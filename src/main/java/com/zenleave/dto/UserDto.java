package com.zenleave.dto;

import com.zenleave.user.Gender;
import com.zenleave.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String phone;
    private boolean onLeave;
    private LocalDateTime returnDate;
    private double leaveDays;
    private int externalActivitiesLimit;
    private Role role;
    private boolean mfaEnabled;

}
