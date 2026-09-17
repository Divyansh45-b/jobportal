package com.JobPortal.JobPortal.Controllers;

import com.JobPortal.JobPortal.Helper.CompanyDto;
import com.JobPortal.JobPortal.Services.CompanyImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    /// constructor injection.
    private final CompanyImpl company;
    public CompanyController (CompanyImpl company)
    {
        this.company = company;
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto companyDto)
    {
        CompanyDto companyDto1 = this.company.create(companyDto);
        return new ResponseEntity<>(companyDto1,HttpStatus.CREATED);
    }

    @GetMapping("/getCompanies")
    public ResponseEntity<List<CompanyDto>> getAllUsers ()
    {
        List<CompanyDto> get = this.company.getAllCompanies();
        return ResponseEntity.ok(get);
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<CompanyDto> update(@RequestBody CompanyDto companyDto,@PathVariable Integer companyId)
    {
        CompanyDto companyDto1 = this.company.update(companyDto,companyId);
        return ResponseEntity.ok(companyDto1);
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Integer companyId)
    {
        String message  = this.company.deleteCompany(companyId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getByName/{keyword}")
    public ResponseEntity<List<CompanyDto>> getByName(@PathVariable String keyword)
    {
       List<CompanyDto> getAllByNames =  this.company.getCompanyByName(keyword);
       return ResponseEntity.ok(getAllByNames);
    }

    @GetMapping("/getByCompanyId/{companyId}")
    public ResponseEntity<CompanyDto> getById(Integer companyId)
    {
        CompanyDto companyDto = this.company.getCompanyById(companyId);
        return ResponseEntity.ok(companyDto);
    }
}
