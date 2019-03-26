package ru.javaops.masterjava.persist.model;

import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.*;

import java.util.Set;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends BaseEntity {
    @Column("full_name")
    private @NonNull String fullName;
    private @NonNull String email;
    private @NonNull UserFlag flag;

    private String city;
    //private Set<String> groups;

    public User(Integer id, String fullName, String email, UserFlag flag, String city) {
        this(fullName, email, flag);
        this.city = city;
        //this.groups = groups;
        this.id=id;
    }
}