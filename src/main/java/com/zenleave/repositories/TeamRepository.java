package com.zenleave.repositories;

import com.zenleave.entities.Team;
import com.zenleave.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findByName(String teamName);

    void deleteByName(String teamName);

    List<Team> findAllByManager(User manager);

        List<Team> findByManagerEmail(String email);

}
