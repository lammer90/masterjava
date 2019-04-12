package ru.javaops.masterjava.web.handler;

import com.sun.xml.ws.api.handler.MessageHandlerContext;
import lombok.extern.slf4j.Slf4j;
import ru.javaops.masterjava.web.AuthUtil;

import javax.xml.ws.handler.MessageContext;
import java.util.List;
import java.util.Map;

@Slf4j
public class SoapServerSecurityHandler extends SoapBaseHandler {

    private String AUTH_HEADER = AuthUtil.encodeBasicAuthHeader(HostsConfig.USER, HostsConfig.PASS);

    @Override
    public boolean handleMessage(MessageHandlerContext context) {
        log.info("Get request, change password");
        Map<String, List<String>> headers = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);

        int code = AuthUtil.checkBasicAuth(headers, AUTH_HEADER);
        if (code != 0) {
            context.put(MessageContext.HTTP_RESPONSE_CODE, code);
            throw new SecurityException();
        }
        return true;
    }

    @Override
    public boolean handleFault(MessageHandlerContext context) {
        return true;
    }
}
