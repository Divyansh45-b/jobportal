package com.JobPortal.JobPortal.Services;

import com.JobPortal.JobPortal.Helper.CompanyDto;


import java.util.List;

public interface CompanyImpl {

     CompanyDto create(CompanyDto companyDto);

     List<CompanyDto> getAllCompanies();

     CompanyDto update (CompanyDto companyDto,Integer companyId);

     String deleteCompany(Integer companyId);

     List<CompanyDto> getCompanyByName(String companyName);

     CompanyDto getCompanyById(Integer companyId);
}
