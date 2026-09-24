package project.example.JobPortal.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.example.JobPortal.Entity.JobSeeker;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker , Long> {


}
