package com.timemaster.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class HoursWorkedResponse {
    private Time timeWorked;
    private Time currentWorkingTime;
    private Time totalTimeWorked;
    private Time overtimeTimeWorked;
    private boolean working = false;
}
