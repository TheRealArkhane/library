package com.libtask.library2.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String email;
}
