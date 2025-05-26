package com.zenleave.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TeamLeaveRequest {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String reason;
}
