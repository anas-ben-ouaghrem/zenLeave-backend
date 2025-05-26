package com.zenleave.repositories;

import com.zenleave.entities.Status;
import com.zenleave.entities.Team;
import com.zenleave.entities.TeamExitPermission;
import com.zenleave.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamExitPermissionRepository extends JpaRepository<TeamExitPermission, Long> {

    List<TeamExitPermission> findByTeamId(Long team_id);

    List<TeamExitPermission> findByTeam_Manager(User manager);

    List<TeamExitPermission> findByTeam(Team team);

    List<TeamExitPermission> findByTeamAndStatus(Team team, Status status);
}
