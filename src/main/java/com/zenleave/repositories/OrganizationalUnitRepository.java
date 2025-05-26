package com.zenleave.repositories;

import com.zenleave.entities.OrganizationalUnit;
import com.zenleave.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnit, Long> {
    Optional<OrganizationalUnit> findByName(String unitName);

    void deleteByName(String unitName);

    Optional<OrganizationalUnit> findByManager(User userToBeDeleted);
}
