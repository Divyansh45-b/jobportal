package project.example.JobPortal.Dto;

import lombok.Data;


@Data
public class RecruiterDto {

    private Long recruiterId;
    private String recruiterName;
    private String email;

    private Long companyId;
}