package com.timemaster.repo;

import com.timemaster.model.User;
import com.timemaster.model.WorkHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkHoursRepo extends JpaRepository<WorkHours, Long> {
    Optional<WorkHours> findByDateAndEndTimeAndUser(Date date, Time endTime, User user);

    @Query(value = "SELECT TIMEDIFF(end_time, start_time ) from work_hours WHERE id = ?1", nativeQuery = true)
    Time getTotalTimeHours(@Param("id") long id);

    @Query(value = "SELECT SEC_TO_TIME( SUM(total_time)) from work_hours WHERE date = ?1 AND user_id = ?2", nativeQuery = true)
    Time getDayTotalTimeWorked(@Param("date") Date date, long userId);

    @Query(value = "SELECT TIMEDIFF(?2, ?1 )", nativeQuery = true)
    Time getTimeDifference(@Param("from") Time from, @Param("to") Time to );

    @Query(value = "SELECT ADDTIME(?1, ?2);", nativeQuery = true)
    Time addTime(@Param("time1") Time time1, @Param("time2") Time time2 );

    @Query(value = "SELECT SUBTIME(?1, ?2);", nativeQuery = true)
    Time subtractTime(@Param("time1") Time time1, @Param("time2") Time time2 );

    @Query(value = "SELECT ?1 > ?2", nativeQuery = true)
    int checkTimeGreaterThan(@Param("time1") Time time1, @Param("time2") Time time2 );

    List<WorkHours> findAllByDate(Date date);
}
