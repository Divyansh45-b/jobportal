package project.example.JobPortal.Dto;

import lombok.Data;

@Data
public class JobSeekerDto {


    private Long jobSeekerId;
    private String name;
    private String email;
    private String phone;
    private String resume;
    private Integer experience;
    private String skills;

}
