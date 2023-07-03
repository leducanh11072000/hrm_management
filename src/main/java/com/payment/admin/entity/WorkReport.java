package com.payment.admin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "work_tasks")
@Getter
@Setter
public class WorkReport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",columnDefinition = "bigint")
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "total_time" ,columnDefinition = "smallint")
    private Integer totalMinute;

    @Column(name = "description", columnDefinition = "varchar(500)")
    private String description;

}
