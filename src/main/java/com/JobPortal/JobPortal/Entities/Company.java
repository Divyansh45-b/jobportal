package com.JobPortal.JobPortal.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name="companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, unique = true)
    private String companyDescription;

    @Column(nullable = false, unique = true)
    private String website;

    private LocalDate createdAt;

    @PrePersist  /// data db mai jaane se phele chalega.
    public void prePersist()
    {
        this.createdAt = LocalDate.now();
    }


    /// mapping
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Job> jobs;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Recruiter> recruiters;
}
