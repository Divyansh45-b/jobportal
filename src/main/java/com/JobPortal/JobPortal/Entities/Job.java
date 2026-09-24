package com.JobPortal.JobPortal.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    @Column(nullable = false)
    private String jobTitle;


    @Column(nullable = false)
    private String jobDescription;

    private String experienceRequired;

    private Double salary;

    private String jobType;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate lastDateToApply;


    /// date ko save krne k liye. @PrePersist -> data db mai insert hone se phele @prepersist chalega.
    @PrePersist
    public void prePersist()
    {
      this.createdAt = LocalDate.now();
    }

/// mapping
   @ManyToOne()
   @JoinColumn(name = "companyId")
    private Company company;

/// mapping
   @ManyToOne()
   private Recruiter recruiter;

   @OneToMany(mappedBy = "job")
   private List<Application> applications;
}
