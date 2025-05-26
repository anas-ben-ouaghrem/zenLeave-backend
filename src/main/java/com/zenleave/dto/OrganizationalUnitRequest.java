package com.zenleave.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrganizationalUnitRequest {

    private String name;

    @Builder.Default
    private List<String> teamNames = new ArrayList<>();
    @Builder.Default
    private List<String> memberEmails = new ArrayList<>();

    private String managerEmail;
}
