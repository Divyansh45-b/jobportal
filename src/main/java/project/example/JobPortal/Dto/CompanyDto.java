package project.example.JobPortal.Dto;

import lombok.Data;

@Data
public class CompanyDto {

    private Long companyId;
    private String companyName;
    private String website;
    private String location;
    private String description;
}
