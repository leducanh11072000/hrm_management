package com.payment.admin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "varchar(100)")
    private String title;

    @Column(name = "description", columnDefinition = "varchar(500)")
    private String description;

    @Column(name = "work_task_id", columnDefinition = "bigint")
    private String workTaskId;
}
