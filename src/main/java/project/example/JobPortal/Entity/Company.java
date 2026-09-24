package project.example.JobPortal.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="companies")
@Data
public class Company {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long companyId;

     private String companyName;
     private String website;
     private String location;
     private String description;

     @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
     private List<Recruiter> recruiters;

     @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
     private List<Job> jobs;

}
