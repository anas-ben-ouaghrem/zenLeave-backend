package com.zenleave.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String currentUserEmail);

    void deleteByEmail(String email);

    Optional<User> findByFirstName(String memberName);
}
