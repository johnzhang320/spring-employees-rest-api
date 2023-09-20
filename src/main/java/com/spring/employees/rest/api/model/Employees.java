package com.spring.employees.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String firstName;

    private String lastName;

    private String emailId;

    private String phoneNo;

    private String interest;

    private String department;

    private String timeZone;

}
