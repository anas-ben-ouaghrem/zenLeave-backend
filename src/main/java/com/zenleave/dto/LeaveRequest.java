package com.zenleave.dto;

import com.zenleave.entities.ExceptionalLeaveType;
import com.zenleave.entities.LeaveType;
import com.zenleave.entities.TimeOfDay;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class LeaveRequest {

    private String userEmail;
    private LeaveType leaveType;
    @Builder.Default
    private ExceptionalLeaveType exceptionalLeaveType = ExceptionalLeaveType.NONE;
    @Builder.Default
    private TimeOfDay timeOfDay = TimeOfDay.INAPPLICABLE;
    @Builder.Default
    private LocalDateTime startDate = LocalDateTime.now();
    @Nullable
    @Builder.Default
    private LocalDateTime endDate = LocalDateTime.now();
    private String reason;
}
