package project.example.JobPortal.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import project.example.JobPortal.Entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    //Derived Methods.
    Page<Application> findByJobSeeker_JobSeekerId(Long jobSeekerId, Pageable pageable);

    Page<Application> findByJob_JobId(Long jobId, Pageable pageable);
}
