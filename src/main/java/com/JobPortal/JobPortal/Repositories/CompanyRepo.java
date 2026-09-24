package com.JobPortal.JobPortal.Repositories;

import com.JobPortal.JobPortal.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {


    List<Company> findByCompanyNameContainingIgnoreCase(String keyword);
}
