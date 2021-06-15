package com.timemaster.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class AccountRequest {
    private String name;
    private String surname;
    private String email;
    private String address;
    private String zip;
    private String state;
    private Long telNumber;
    private Time dayWorkHours;

    private String role;
}
