package project.example.JobPortal.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.example.JobPortal.Entity.ApplicationStatus;

import java.time.LocalDate;

@Data
public class ApplicationDto {

    private Long applicationId;

    private LocalDate appliedDate;

    private ApplicationStatus status;

    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotNull(message = "Job seeker ID is required")
    private Long jobSeekerId;
}

