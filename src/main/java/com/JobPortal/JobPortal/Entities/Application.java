package com.JobPortal.JobPortal.Entities;

import com.JobPortal.JobPortal.Enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Application {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationId;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    private Job job;

    @ManyToOne
    private JobSeeker jobSeeker;
}
