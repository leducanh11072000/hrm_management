package com.payment.admin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends BaseEntity {

    @Id
    @Column(name="code",nullable = false, columnDefinition = "varchar(100)")
    private String code;

    @Column(name="description", columnDefinition = "varchar(500)")
    private String description;

}
