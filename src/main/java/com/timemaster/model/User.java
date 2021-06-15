package com.timemaster.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "workHours", "vacations" , "vacationRequests", "dayReports"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String email;
    private Long telNumber;

    private String password;
    private String role;
    private Time dayWorkHours  = Time.valueOf("08:00:00");

    @OneToOne(cascade = CascadeType.ALL)
    private Address address = new Address();

    @OneToOne(cascade = CascadeType.ALL)
    private Settings settings = new Settings();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WorkHours> workHours = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vacation> vacations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<VacationRequest> vacationRequests = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DayReport> dayReports = new ArrayList<>();

    public User(String name, String surname, String email, String password, String role, Time dayWorkHours, Address address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dayWorkHours = dayWorkHours;
        this.address = address;
    }
}
