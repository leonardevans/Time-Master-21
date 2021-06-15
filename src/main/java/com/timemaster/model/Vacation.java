package com.timemaster.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int days;
    private int year;

    @ManyToOne
    private User user;

    public Vacation(int days, int year, User user) {
        this.days = days;
        this.year = year;
        this.user = user;
    }
}
