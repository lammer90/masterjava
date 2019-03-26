package ru.javaops.masterjava.persist.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode()
@NoArgsConstructor
public class Group{

    private @NonNull String name;
    private @NonNull GroupType type;
    private @NonNull Integer project;
}
