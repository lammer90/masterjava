package ru.javaops.masterjava.upload;

import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.GroupDao;
import ru.javaops.masterjava.persist.dao.ProjectDao;
import ru.javaops.masterjava.persist.model.Group;
import ru.javaops.masterjava.persist.model.Project;
import ru.javaops.masterjava.persist.model.type.GroupType;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectProcessor {

    public static Map<String, Integer> saveAndGetProjects(StaxStreamProcessor processor) throws XMLStreamException {

        ProjectDao projectDao = DBIProvider.getDao(ProjectDao.class);
        GroupDao groupDao = DBIProvider.getDao(GroupDao.class);

        Map<Project, List<Group>> projects = new HashMap<>();
        Map<String, Integer> groupList = new HashMap<>();

        while (processor.startElement("Project", "Projects")) {
            String nameProj = processor.getAttribute("name");
            String desc = processor.getElementValue("description");
            Project project = new Project(nameProj, desc);
            int key = projectDao.insertGeneratedId(project);

            if (key == 0){
                key = projectDao.getAll().stream().filter(p -> p.getName().equals(nameProj)).findFirst().orElse(null).getId();
            }

            List<Group> groups = new ArrayList<>();
            while (processor.startElement("Group", "Project")) {

                String nameGroup = processor.getAttribute("name");
                String type = processor.getAttribute("type");
                Group group = new Group(nameGroup, GroupType.valueOf(type), key);
                int groupKey = groupDao.insertGeneratedId(group);
                groups.add(group);
                groupList.put(group.getName(), groupKey);
            }

            projects.put(project, groups);
        }

        return groupList;
    }
}
