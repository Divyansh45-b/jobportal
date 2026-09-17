package com.JobPortal.JobPortal.Repositories;


import com.JobPortal.JobPortal.Entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer> {


}
