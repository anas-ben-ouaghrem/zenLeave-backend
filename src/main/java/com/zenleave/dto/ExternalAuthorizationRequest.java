package com.zenleave.dto;

import com.zenleave.entities.LeaveDuration;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ExternalAuthorizationRequest {

    private LeaveDuration leaveDuration;
    private LocalDateTime date;
    private String userEmail;
    private String reason;
}
