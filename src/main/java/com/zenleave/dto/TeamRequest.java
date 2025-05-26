package com.zenleave.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamRequest {

    private String name;
    private String description;
    private String teamLeadEmail;
    @Builder.Default
    private String[] teamMembersEmails = new String[0];
    private String organizationalUnitName;
    @Builder.Default
    private int minimumAttendance = 10;
}
