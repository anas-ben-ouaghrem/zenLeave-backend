package com.zenleave.repositories;

import com.zenleave.entities.Status;
import com.zenleave.entities.Team;
import com.zenleave.entities.TeamLeave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamLeaveRepository extends JpaRepository<TeamLeave,Long> {
    List<TeamLeave> findByTeamId(Long teamId);

    List<TeamLeave> findByTeamAndStatus(Team team, Status status);
}
