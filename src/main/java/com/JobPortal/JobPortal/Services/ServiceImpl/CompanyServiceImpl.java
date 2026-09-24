package com.JobPortal.JobPortal.Services.ServiceImpl;

import com.JobPortal.JobPortal.Entities.Company;
import com.JobPortal.JobPortal.Helper.CompanyDto;
import com.JobPortal.JobPortal.Repositories.CompanyRepo;
import com.JobPortal.JobPortal.Services.CompanyImpl;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyImpl {


    private final ModelMapper modelMapper;

    private final CompanyRepo companyRepo;

    public CompanyServiceImpl(CompanyRepo companyRepo,ModelMapper modelMapper)
    {
           this.modelMapper = modelMapper;
           this.companyRepo = companyRepo;
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {

           Company company = this.modelMapper.map(companyDto, Company.class);
           Company savedCompany = this.companyRepo.save(company);
        return this.modelMapper.map(savedCompany,CompanyDto.class);
    }

    @Override
    public List<CompanyDto> getAllCompanies() {

        List<Company> company = this.companyRepo.findAll();

       return company.stream()
               .map(company1 -> this.modelMapper.map(company1, CompanyDto.class))
               .toList();
    }

    @Override
    public CompanyDto update(CompanyDto companyDto,Integer companyId) {

        Company company = this.companyRepo.findById(companyId).orElseThrow(()-> new RuntimeException("companyId not found"));

        company.setCompanyName(companyDto.getCompanyName());
        company.setWebsite(companyDto.getWebsite());
        company.setCompanyDescription(companyDto.getCompanyDescription());
        company.setLocation(companyDto.getLocation());

        Company saved =  this.companyRepo.save(company);
        return this.modelMapper.map(saved,CompanyDto.class);
    }

    @Override
    public String deleteCompany(Integer companyId) {

        Company company = this.companyRepo
                .findById(companyId).orElseThrow(()-> new RuntimeException("companyId not found"));

        this.companyRepo.delete(company);

       return "company deleted successfully";
    }

    @Override
    public List<CompanyDto> getCompanyByName(String keyword) {

       List<Company> companies = this.companyRepo.findByCompanyNameContainingIgnoreCase(keyword);

       return companies.stream()
               .map(company -> this.modelMapper.map(company, CompanyDto.class))
               .toList();
    }

    @Override
    public CompanyDto getCompanyById(Integer companyId) {

        Company company = this.companyRepo.findById(companyId)
                                .orElseThrow(()-> new RuntimeException("companyId not found"));

        return this.modelMapper.map(company, CompanyDto.class);
    }
}
