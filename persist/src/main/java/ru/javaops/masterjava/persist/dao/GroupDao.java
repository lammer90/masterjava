package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import ru.javaops.masterjava.persist.model.Group;

import java.util.List;

public abstract class GroupDao implements AbstractDao {

    @SqlUpdate("INSERT INTO groups (name, type, project) VALUES (:name, CAST(:type AS GROUP_TYPE), :project)")
    abstract void insert(@BindBean Group group);

    @SqlBatch("INSERT INTO groups (name, type, project) VALUES (:name, CAST(:type AS GROUP_TYPE), :project)" +
            "ON CONFLICT DO NOTHING")
    public abstract int[] insertBatch(@BindBean List<Group> groups);

    @SqlQuery("SELECT * FROM groups ORDER BY name")
    public abstract List<Group> getAll();

    @SqlUpdate("TRUNCATE groups")
    @Override
    public abstract void clean();
}
