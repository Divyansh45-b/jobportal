package com.JobPortal.JobPortal.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seekerId;

    private String fullName;

    private String email;

    private String contactNumber;

    private String skills;

    private String education;

    private String experience;

    @OneToMany(mappedBy = "jobSeeker")
    private List<Application> applications;
}
