package com.timemaster.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DayReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date day;
    private Time timeWorked;
    private Time dayWorkTime;
    private Time overTime;

    @ManyToOne
    private User user;

    public DayReport(Time timeWorked, Time dayWorkTime, Time overTime, User user) {
        this.timeWorked = timeWorked;
        this.dayWorkTime = dayWorkTime;
        this.overTime = overTime;
        this.user = user;
    }
}
