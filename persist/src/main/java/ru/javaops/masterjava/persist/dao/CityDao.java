package ru.javaops.masterjava.persist.dao;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import ru.javaops.masterjava.persist.model.City;

import java.util.List;

public abstract class CityDao implements AbstractDao {

    @SqlUpdate("INSERT INTO cities (id, name) VALUES (:id, name)")
    abstract void insert(@BindBean City city);

    @SqlBatch("INSERT INTO cities (id, name) VALUES (:id, :name)" +
            "ON CONFLICT DO NOTHING")
    public abstract int[] insertBatch(@BindBean List<City> cities);

    @SqlQuery("SELECT * FROM cities ORDER BY name")
    public abstract List<City> getAll();

    @SqlUpdate("TRUNCATE cities")
    @Override
    public abstract void clean();
}
