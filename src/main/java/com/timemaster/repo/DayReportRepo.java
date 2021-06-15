package com.timemaster.repo;

import com.timemaster.model.DayReport;
import com.timemaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DayReportRepo extends JpaRepository<DayReport, Long> {
    Optional<DayReport> findByDayAndUser(Date date, User user);

    List<DayReport> findByDay(Date date);

    @Query(value = "SELECT * FROM day_report WHERE day LIKE %?1% AND user_id = ?2 ", nativeQuery = true)
    List<DayReport> findByMonthAndUser(@Param("monthString") String monthString, @Param("userId") long userId);

    @Query(value = "SELECT * FROM day_report WHERE day LIKE %?1% ", nativeQuery = true)
    List<DayReport> findByMonth(@Param("monthString") String monthString);

    @Query(value = "SELECT * FROM day_report WHERE day LIKE %?1% AND user_id = ?2 ", nativeQuery = true)
    List<DayReport> findByYearAndUser(@Param("monthString") String monthString, long userId);

    @Query(value = "SELECT * FROM day_report WHERE day LIKE %?1%", nativeQuery = true)
    List<DayReport> findByYear(@Param("monthString") String monthString);
}
