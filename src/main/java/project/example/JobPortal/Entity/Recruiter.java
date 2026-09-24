package project.example.JobPortal.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "recruiters")
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruiterId;

    private String recruiterName;

    private String email;

    @ManyToOne
    @JoinColumn(name = "companyId")

    private Company company;

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)

    private List<Job> jobs;

}
