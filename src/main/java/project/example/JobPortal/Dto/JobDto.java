package project.example.JobPortal.Dto;

import lombok.Data;


import java.time.LocalDate;

@Data
public class JobDto {

    private Long jobId;

    private String jobTitle;
    private String location;
    private String description;
    private Integer experience;
    private String skills;
    private Integer vacancies;
    private LocalDate postedDate;
    private LocalDate lastDateToApply;

    private Long companyId;
    private Long recruiterId;

}


