package com.timemaster.repo;

import com.timemaster.model.User;
import com.timemaster.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VacationRepo extends JpaRepository<Vacation, Long> {
    Optional<Vacation> findByUserAndYear(User user, int year);
}
