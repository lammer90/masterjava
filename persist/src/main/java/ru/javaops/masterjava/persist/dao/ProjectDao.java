package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import ru.javaops.masterjava.persist.model.Project;

import java.util.List;

public abstract class ProjectDao implements AbstractDao {

    @SqlUpdate("INSERT INTO projects (name, description) VALUES (:name, :description)")
    abstract void insert(@BindBean Project project);

    @SqlBatch("INSERT INTO projects (name, description) VALUES (:name, :description)" +
            "ON CONFLICT DO NOTHING")
    public abstract int[] insertBatch(@BindBean List<Project> projects);

    @SqlQuery("SELECT * FROM projects ORDER BY name")
    public abstract List<Project> getAll();

    @SqlUpdate("TRUNCATE projects")
    @Override
    public abstract void clean();
}
