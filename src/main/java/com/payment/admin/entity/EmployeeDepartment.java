package com.payment.admin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee_deparment")
@Getter
@Setter
public class EmployeeDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",columnDefinition = "bigint")
    private Long userId;

    @Column(name = "department_id",columnDefinition = "bigint")
    private Long departmentId;


}
