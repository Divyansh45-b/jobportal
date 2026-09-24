package project.example.JobPortal.Service;

import org.springframework.data.domain.Page;
import project.example.JobPortal.Dto.ApplicationDto;




public interface ApplicationService {
    ApplicationDto createApplication(ApplicationDto applicationDto,
                                     Long jobId,
                                     Long jobSeekerId);

    Page<ApplicationDto> getAllApplications(int page, int size);

    ApplicationDto getApplicationById(Long applicationId);

    ApplicationDto updateApplication(Long applicationId,
                                     ApplicationDto applicationDto);

    void deleteApplication(Long applicationId);

    Page<ApplicationDto> getApplicationsByJob(Long jobId, int page, int  size);

    Page<ApplicationDto> getApplicationsByJobSeeker(Long jobSeekerId, int page , int  size);
}
