package project.example.JobPortal.Service.ServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.example.JobPortal.Dto.JobSeekerDto;
import project.example.JobPortal.Entity.JobSeeker;
import project.example.JobPortal.Helper.ResourceNotFound;
import project.example.JobPortal.Repository.JobSeekerRepository;
import project.example.JobPortal.Service.JobSeekerService;


@Service
public class JobSeekerServiceImpl implements JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;

    public JobSeekerServiceImpl(JobSeekerRepository jobSeekerRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
    }

    @Override
    public JobSeekerDto createJobSeeker(JobSeekerDto jobSeekerDto) {

        JobSeeker jobSeeker = new JobSeeker();

        jobSeeker.setName(jobSeekerDto.getName());
        jobSeeker.setEmail(jobSeekerDto.getEmail());
        jobSeeker.setPhone(jobSeekerDto.getPhone());
        jobSeeker.setResume(jobSeekerDto.getResume());
        jobSeeker.setExperience(jobSeekerDto.getExperience());
        jobSeeker.setSkills(jobSeekerDto.getSkills());

        JobSeeker savedJobSeeker = jobSeekerRepository.save(jobSeeker);

        return convertToDto(savedJobSeeker);
    }

    @Override
    public Page<JobSeekerDto> getAllJobSeekers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("jobSeekerId").ascending());

        Page<JobSeeker> jobSeekers = this.jobSeekerRepository.findAll(pageable);

        return jobSeekers.map(this::convertToDto);
    }

    @Override
    public void deleteJobSeeker(Long jobSeekerId) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new ResourceNotFound("this jobSeekerId is not present here"));

        jobSeekerRepository.delete(jobSeeker);
    }

    @Override
    public JobSeekerDto updateJobSeeker(Long jobSeekerId,
                                        JobSeekerDto jobSeekerDto) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new ResourceNotFound("I can not update , because no jobSeeker id here "));

        jobSeeker.setName(jobSeekerDto.getName());
        jobSeeker.setEmail(jobSeekerDto.getEmail());
        jobSeeker.setPhone(jobSeekerDto.getPhone());
        jobSeeker.setResume(jobSeekerDto.getResume());
        jobSeeker.setExperience(jobSeekerDto.getExperience());
        jobSeeker.setSkills(jobSeekerDto.getSkills());

        JobSeeker updatedJobSeeker = jobSeekerRepository.save(jobSeeker);

        return convertToDto(updatedJobSeeker);
    }

    private JobSeekerDto convertToDto(JobSeeker jobSeeker) {

        JobSeekerDto dto = new JobSeekerDto();

        dto.setJobSeekerId(jobSeeker.getJobSeekerId());
        dto.setName(jobSeeker.getName());
        dto.setEmail(jobSeeker.getEmail());
        dto.setPhone(jobSeeker.getPhone());
        dto.setResume(jobSeeker.getResume());
        dto.setExperience(jobSeeker.getExperience());
        dto.setSkills(jobSeeker.getSkills());

        return dto;
    }
}