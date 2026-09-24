package com.JobPortal.JobPortal.Repositories;

import com.JobPortal.JobPortal.Entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {


    List<Job> findJobByJobTitleContainingIgnoreCase(String JobTitle);

}
