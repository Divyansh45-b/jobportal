package project.example.JobPortal.Dto;

import lombok.Data;
import project.example.JobPortal.Entity.ApplicationStatus;

import java.time.LocalDate;

@Data
public class ApplicationDto {

    private Long applicationId;
    private LocalDate appliedDate;
    private ApplicationStatus status;

    private Long jobId;
    private Long jobSeekerId;
}
