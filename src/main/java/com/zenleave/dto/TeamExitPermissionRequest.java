package com.zenleave.dto;

import com.zenleave.entities.LeaveDuration;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TeamExitPermissionRequest {
    private LeaveDuration leaveDuration;
    private LocalDateTime date;
    private String teamName;
    private String reason;
}
