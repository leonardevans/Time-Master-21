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
public class WorkHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date date;
    private Time startTime;
    private Time endTime;
    private Time totalTime;

    @ManyToOne
    private User user;

    public WorkHours(Date date, Time startTime, User user) {
        this.date = date;
        this.startTime = startTime;
        this.user = user;
    }
}
