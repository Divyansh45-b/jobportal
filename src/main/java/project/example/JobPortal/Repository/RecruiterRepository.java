package project.example.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.example.JobPortal.Entity.Recruiter;

public interface RecruiterRepository  extends JpaRepository<Recruiter, Long> {
}
