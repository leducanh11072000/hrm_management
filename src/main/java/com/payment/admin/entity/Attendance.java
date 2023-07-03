package com.payment.admin.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "leave_request")
@Getter
@Setter
public class Attendance extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_attendance")
    private Date dateAttendance;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_in")
    private Date timeIn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_out")
    private Date timeOut;
}
