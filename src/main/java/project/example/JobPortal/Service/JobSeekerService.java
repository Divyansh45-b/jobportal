package project.example.JobPortal.Service;

import org.springframework.data.domain.Page;
import project.example.JobPortal.Dto.JobSeekerDto;

import java.util.List;

public interface JobSeekerService {


    JobSeekerDto createJobSeeker(JobSeekerDto jobSeekerDto);

    Page<JobSeekerDto> getAllJobSeekers(int page, int size);

    void deleteJobSeeker(Long jobSeekerId);

    JobSeekerDto updateJobSeeker(Long jobSeekerId, JobSeekerDto jobSeekerDto);

}
