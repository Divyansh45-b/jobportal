package project.example.JobPortal.Dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobDto {

    private Long jobId;

    @NotBlank(message = "Job title is required")
    @Size(max = 100, message = "Job title must not exceed 100 characters")
    private String jobTitle;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    private Integer experience;

    @NotBlank(message = "Skills are required")
    private String skills;

    @NotNull(message = "Vacancies are required")
    @Min(value = 1, message = "Vacancies must be at least 1")
    private Integer vacancies;

    private LocalDate postedDate;

    @FutureOrPresent(message = "Last date to apply cannot be in the past")
    private LocalDate lastDateToApply;

    private Long companyId;
    private Long recruiterId;
}

