package com.zenleave.services;

import com.zenleave.dto.TeamExitPermissionRequest;
import com.zenleave.entities.Status;
import com.zenleave.entities.Team;
import com.zenleave.entities.TeamExitPermission;
import com.zenleave.exceptions.UnauthorizedActionException;
import com.zenleave.repositories.TeamExitPermissionRepository;
import com.zenleave.repositories.TeamRepository;
import com.zenleave.user.Role;
import com.zenleave.user.User;
import com.zenleave.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamExitPermissionService {

    private final TeamExitPermissionRepository teamExitPermissionRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final MailingService mailingService;

    public TeamExitPermission createTeamExitPermission(String currentUserEmail, TeamExitPermissionRequest request) {

        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Team team = teamRepository.findByName(request.getTeamName())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        if (currentUser != team.getManager() && currentUser.getRole() == Role.USER) {
            throw new UnauthorizedActionException("You are not authorized to perform this action");
        }

        TeamExitPermission teamExitPermission = TeamExitPermission.builder()
                .leaveDuration(request.getLeaveDuration())
                .reason(request.getReason())
                .startDate(request.getDate())
                .endDate(request.getDate().plusMinutes(request.getLeaveDuration().getDuration()))
                .status(Status.PENDING)
                .createdAt(LocalDateTime.now())
                .team(team)
                .build();
        this.mailingService.sendMail(team.getManager().getEmail(),"Exit Permissions request created", "Your Team Exit Permissions request has been created");
        this.mailingService.sendMail(team.getOrganizationalUnit().getManager().getEmail(),"Exit Permissions request created", "Your team manager " + currentUser.getFirstName() + " " + currentUser.getLastName() + " for the team: " + team.getName() +  " has created a Team Exit Permissions request");
        return teamExitPermissionRepository.save(teamExitPermission);
    }

    public void treatTeamExitPermission(Long id, Status status, String currentUserEmail) {
        TeamExitPermission teamExitPermission = teamExitPermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exit Permission not found"));
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (currentUser.getRole() != Role.ADMIN ) {
            if (currentUser != teamExitPermission.getTeam().getManager() ) {
                throw new UnauthorizedActionException("You are not authorized to treat this Exit Permissions");
            }
        }

        Team team = teamExitPermission.getTeam();

        if (status == Status.ACCEPTED) {
            // Erase other pending team exit permissions from the same team
            List<TeamExitPermission> pendingTeamExitPermissions = teamExitPermissionRepository
                    .findByTeamAndStatus(teamExitPermission.getTeam(), Status.PENDING);

            for (TeamExitPermission pendingPermission : pendingTeamExitPermissions) {
                if (!pendingPermission.getId().equals(id)) {
                    // Erase or update status for other pending permissions
                    // Here, assuming you want to erase them, you can use your own logic
                    teamExitPermissionRepository.delete(pendingPermission);
                }
            }

            // Add special actions for accepted status here
            log.info("Team Exit Permissions accepted");
        } else if (status == Status.REJECTED) {
            // Add special actions for rejected status here
            log.info("Team Exit Permissions rejected");
        }

        teamExitPermission.setStatus(status);
        teamExitPermissionRepository.saveAndFlush(teamExitPermission);
        this.mailingService.sendMail(team.getManager().getEmail(),"Team Exit Permission treated", "Your Team Exit Permission with id " + id + " has been " + status);
    }

    public List<TeamExitPermission> getAllTeamExitPermissions() {
        return teamExitPermissionRepository.findAll();
    }

    public TeamExitPermission getTeamExitPermissionById(Long id) {
        return teamExitPermissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Exit Permissions not found"));
    }

    public void deleteExitPermission(Long id) {
        teamExitPermissionRepository.deleteById(id);
        log.info("Exit Permissions deleted");
    }

    public List<TeamExitPermission> getTeamExitPermissionByTeamId(Long userId) {
        return teamExitPermissionRepository.findByTeamId(userId);
    }

    public List<TeamExitPermission> getTeamExitPermissionsByTeamManager(String managerEmail) {
        User manager = userRepository.findByEmail(managerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return teamExitPermissionRepository.findByTeam_Manager(manager);
    }

    public List<TeamExitPermission> getTeamExitPermissionsByTeam(String teamName) {
        Team team = teamRepository.findByName(teamName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return teamExitPermissionRepository.findByTeam(team);
    }

    public TeamExitPermission updateTeamExitPermission(Long id, TeamExitPermissionRequest request) {
        TeamExitPermission teamExitPermission = teamExitPermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exit Permissions not found"));
        teamExitPermission.setLeaveDuration(request.getLeaveDuration());
        teamExitPermission.setStartDate(request.getDate());
        teamExitPermission.setReason(request.getReason());
        teamExitPermission.setEndDate(request.getDate().plusMinutes(request.getLeaveDuration().getDuration()));
        teamExitPermissionRepository.saveAndFlush(teamExitPermission);
        this.mailingService.sendMail(teamExitPermission.getTeam().getManager().getEmail(),"Exit Permissions request updated", "Your Exit Permissions request has been updated");
        this.mailingService.sendMail(teamExitPermission.getTeam().getOrganizationalUnit().getManager().getEmail(),"Exit Permissions request updated", "Your team manager " + teamExitPermission.getTeam().getManager().getFirstName() + " " + teamExitPermission.getTeam().getManager().getLastName() + " for the team: " + teamExitPermission.getTeam().getName() +  " has updated a Team Exit Permissions request");
        return teamExitPermission;
    }

    public List<TeamExitPermission> getTeamExitPermissionsByUser(String currentUserEmail) {
        User user = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return teamExitPermissionRepository.findByTeam(user.getTeam());
    }
}

