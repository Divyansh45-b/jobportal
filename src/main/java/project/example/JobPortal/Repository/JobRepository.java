package project.example.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.example.JobPortal.Entity.Job;



public interface JobRepository extends JpaRepository<Job,Long> {


}
