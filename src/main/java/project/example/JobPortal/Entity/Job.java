package project.example.JobPortal.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name ="jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String jobTitle;
    private String location;
    private String description;
    private Integer experience;
    private String skills;
    private Integer vacancies;
    private LocalDate postedDate;
    private LocalDate lastDateToApply;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;


}

