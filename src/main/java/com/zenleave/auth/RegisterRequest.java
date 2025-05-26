package com.zenleave.auth;

import com.zenleave.user.Gender;
import com.zenleave.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String password;
    private Role role;
    private String phone;
    @Builder.Default
    private boolean mfaEnabled = false;
}
