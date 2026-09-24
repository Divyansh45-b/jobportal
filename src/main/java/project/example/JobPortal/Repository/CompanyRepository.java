package project.example.JobPortal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.example.JobPortal.Entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {


}
