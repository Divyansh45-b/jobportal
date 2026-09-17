package com.JobPortal.JobPortal.Repositories;

import com.JobPortal.JobPortal.Entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo  extends JpaRepository<Application,Integer> {

    List<Application> findAllByJob_JobId(Integer jobId);

    List<Application> findAllByJobSeeker_SeekerId(Integer seekerId);
}
