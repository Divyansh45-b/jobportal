package project.example.JobPortal.Controller;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.example.JobPortal.Dto.CompanyDto;
import project.example.JobPortal.Service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies/api/")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService)
    {
        this.companyService =  companyService;
    }

    @PostMapping("/create")
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto)
    {
        return this.companyService.createCompany(companyDto);
    }

    @GetMapping("/getAllCompanies")
    public Page<CompanyDto> getALlCompanies(@RequestParam(defaultValue = "0")
                                                int page,@RequestParam(defaultValue = "2") int size)
    {
        return this.companyService.getAllCompanies(page,size);
    }

    @DeleteMapping("/deleteById/{companyId}")
    public String deleteCompanyById(@PathVariable  Long companyId)
    {
        this.companyService.deleteCompanyById(companyId);
        return "company deleted successfully";
    }

    @PutMapping("/update/{companyId}")
    public CompanyDto updateCompany(@PathVariable Long companyId,
                                    @RequestBody CompanyDto companyDto)
    {
        return this.companyService.updateCompany(companyId , companyDto);
    }

    @GetMapping("/getCompanyById/{companyId}")
    public CompanyDto getCompanyById(@PathVariable Long companyId)
    {
         return this.companyService.getCompanyById(companyId);
    }

}
