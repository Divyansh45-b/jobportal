package project.example.JobPortal.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "job_seekers")
@Data
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobSeekerId;

    private String name;

    private String email;

    private String phone;

    private String resume;

    private Integer experience;

    private String skills;
}