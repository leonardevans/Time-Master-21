package com.timemaster.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VacationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int duration;

    private Date bookDate;

    private Date startDate;
    private Date endDate;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean accepted;

    @ManyToOne
    private User user;

    public VacationRequest(Date bookDate, Date startDate, Date endDate , User user) {
        this.bookDate = bookDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }
}
