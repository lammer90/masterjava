package dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import model.PostResult;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.dao.AbstractDao;

import java.util.Collection;
import java.util.List;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class PostResultDAO implements AbstractDao {

    @SqlUpdate("TRUNCATE post_results")
    @Override
    public abstract void clean();

    @SqlQuery("SELECT * FROM post_results")
    public abstract List<PostResult> getAll();

    @SqlUpdate("INSERT INTO post_results (date_time, addresses_to, addresses_cc, subject, text, success) VALUES (:dateTime, :addressesTO, :addressesCC, :subject, :text, :success)")
    public abstract void insert(@BindBean PostResult postResult);

    @SqlBatch("INSERT INTO post_results (date_time, addresses_to, addresses_cc, subject, text, success) VALUES (:dateTime, :addressesTO, :addressesCC, :subject, :text, :success)")
    public abstract void insertBatch(@BindBean Collection<PostResult> postResults);
}
