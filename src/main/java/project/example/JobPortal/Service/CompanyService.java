package project.example.JobPortal.Service;


import org.springframework.data.domain.Page;
import project.example.JobPortal.Dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    //crud operations
    CompanyDto createCompany(CompanyDto companyDto);
    Page<CompanyDto> getAllCompanies(int page, int  size);
    CompanyDto updateCompany(Long companyId, CompanyDto companyDto);
    void deleteCompanyById(Long companyId);

    //extra operations
    CompanyDto getCompanyById(Long companyId);


}
