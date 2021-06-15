package com.timemaster.repo;

import com.timemaster.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface VacationRequestRepo extends JpaRepository<VacationRequest, Long> {
    @Query(value = "SELECT * FROM vacation_request WHERE start_date >= ?1", nativeQuery = true)
    List<VacationRequest> findAllByStartDateGreaterThanEqual(@Param("startDate") Date startDate);
}
