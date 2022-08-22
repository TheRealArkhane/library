package com.libtask.library2.entities;

import lombok.*;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    @NonNull
    private final String email;
    @NonNull
    private final String password;
}
