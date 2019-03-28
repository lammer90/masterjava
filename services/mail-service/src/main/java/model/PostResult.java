package model;


import com.bertoncelj.jdbi.entitymapper.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResult {
    @NonNull @Column("date_time") private LocalDateTime dateTime;
    @NonNull @Column("addresses_to")  private String addressesTO;
    @NonNull @Column("addresses_cc") private String addressesCC;
    private String subject;
    private String text;
    private boolean success;
}
