package com.JobPortal.JobPortal.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recruiterId;

    private String recruiterName;

    private  String contactNumber;


   @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<Job> jobs;

   @ManyToOne
   private Company company;

}
