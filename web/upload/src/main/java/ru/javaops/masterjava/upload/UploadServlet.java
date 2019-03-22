package ru.javaops.masterjava.upload;

import org.thymeleaf.context.WebContext;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.UserDao;
import ru.javaops.masterjava.persist.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static ru.javaops.masterjava.common.web.ThymeleafListener.engine;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10) //10 MB in memory limit
public class UploadServlet extends HttpServlet {

    private final UserProcessor userProcessor = new UserProcessor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale());
        engine.process("upload", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final WebContext webContext = new WebContext(req, resp, req.getServletContext(), req.getLocale());
        int k = Integer.parseInt(req.getParameter("kol"));

        try {
//            http://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
            Part filePart = req.getPart("fileToUpload");
            if (filePart.getSize() == 0) {
                throw new IllegalStateException("Upload file have not been selected");
            }
            try (InputStream is = filePart.getInputStream()) {
                List<User> users = userProcessor.process(is);

                List<User> repeatUsers = sendToBase(users, k);

                webContext.setVariable("users", repeatUsers);
                engine.process("result", webContext, resp.getWriter());
            }
        } catch (Exception e) {
            webContext.setVariable("exception", e);
            engine.process("exception", webContext, resp.getWriter());
        }
    }

    private List<User> sendToBase(List<User> users, int k) throws InterruptedException, ExecutionException {
        List<User> result = Collections.synchronizedList(new ArrayList<>());
        CompletionService<ResultPair> service = new ExecutorCompletionService<>(Executors.newFixedThreadPool(8));
        ExecutorService errorService = Executors.newFixedThreadPool(8);
        UserDao dao = DBIProvider.getDao(UserDao.class);

        Map<Integer, List<User>> map = users.stream().collect(Collectors.groupingBy(u -> users.indexOf(u) / k));
        map.forEach((key, value) -> service.submit(() -> new ResultPair(dao.insertBatchGeneratedId(value), key)));

        for (int i = 0; i < map.size(); i++) {
            ResultPair pair = service.poll(10, TimeUnit.SECONDS).get();
            errorService.submit(() -> {
                int[] c = pair.getC();
                for (int j = 0; j < c.length; j++) {
                    if (c[j] == 0) {
                        result.add(map.get(pair.getNumberOfMap()).get(j));
                    }
                }
            });
        }
        errorService.shutdown();
        errorService.awaitTermination(10, TimeUnit.SECONDS);
        return result;
    }

    private class ResultPair {
        int[] c;
        int numberOfMap;

        public ResultPair(int[] c, int numberOfMap) {
            this.c = c;
            this.numberOfMap = numberOfMap;
        }

        public int[] getC() {
            return c;
        }

        public int getNumberOfMap() {
            return numberOfMap;
        }
    }
}
