package project.example.JobPortal.Service;

import org.springframework.data.domain.Page;
import project.example.JobPortal.Dto.JobDto;

import java.util.List;

public interface JobService {

    JobDto createJob(JobDto jobDto, Long companyId, Long recruiterId);

    Page<JobDto> getAllJobs(int page, int size);

    JobDto getJobById(Long jobId);

    JobDto updateJob(Long jobId, JobDto jobDto);

    void deleteJob(Long jobId);

    List<JobDto> getJobsByCompany(Long companyId);

    List<JobDto> getJobsByRecruiter(Long recruiterId);

}
