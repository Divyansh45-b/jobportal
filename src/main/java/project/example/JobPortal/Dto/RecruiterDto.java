package project.example.JobPortal.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecruiterDto {

    private Long recruiterId;

    @NotBlank(message = "Recruiter name is required")
    private String recruiterName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    private Long companyId;
}