package com.zenleave.repositories;

import com.zenleave.entities.EmployeeLeave;
import com.zenleave.entities.Status;
import com.zenleave.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {
    List<EmployeeLeave> findAllByUser_Id(Integer id);

    List<EmployeeLeave> findByUserAndStatus(User userRequestingLeave, Status status);
}
