package project.example.JobPortal.Service.ServiceImpl;


import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.example.JobPortal.Dto.CompanyDto;
import project.example.JobPortal.Entity.Company;
import project.example.JobPortal.Repository.CompanyRepository;
import project.example.JobPortal.Service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {

        Company company = new Company();

        company.setCompanyName(companyDto.getCompanyName());
        company.setWebsite(companyDto.getWebsite());
        company.setLocation(companyDto.getLocation());
        company.setDescription(companyDto.getDescription());

        Company savedCompany = companyRepository.save(company);

        return convertToDto(savedCompany);
    }

    @Override
    public Page<CompanyDto> getAllCompanies(int page , int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("companyId").ascending());

        Page<Company> page1 = this.companyRepository.findAll(pageable);

        return page1.map(this::convertToDto);
    }

    @Override
    public CompanyDto getCompanyById(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return convertToDto(company);
    }



    @Override
    public CompanyDto updateCompany(Long companyId, CompanyDto companyDto) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setCompanyName(companyDto.getCompanyName());
        company.setWebsite(companyDto.getWebsite());
        company.setLocation(companyDto.getLocation());
        company.setDescription(companyDto.getDescription());

        Company updatedCompany = companyRepository.save(company);

        return convertToDto(updatedCompany);
    }

    @Override
    public void deleteCompanyById(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyRepository.delete(company);
    }

    private CompanyDto convertToDto(Company company) {

        CompanyDto dto = new CompanyDto();

        dto.setCompanyId(company.getCompanyId());
        dto.setCompanyName(company.getCompanyName());
        dto.setWebsite(company.getWebsite());
        dto.setLocation(company.getLocation());
        dto.setDescription(company.getDescription());

        return dto;
    }
}