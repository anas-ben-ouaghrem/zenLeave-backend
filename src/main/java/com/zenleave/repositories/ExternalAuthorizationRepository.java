package com.zenleave.repositories;

import com.zenleave.entities.ExternalAuthorization;
import com.zenleave.entities.Status;
import com.zenleave.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExternalAuthorizationRepository extends JpaRepository<ExternalAuthorization, Long> {
    List<ExternalAuthorization> findByUserId(Integer userId);

    List<ExternalAuthorization> findByUser_Team_Manager(User manager);

    List<ExternalAuthorization> findByUser(User user);

    List<ExternalAuthorization> findByUserAndStatus(User user, Status status);
}
