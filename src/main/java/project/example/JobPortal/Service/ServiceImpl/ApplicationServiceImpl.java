package project.example.JobPortal.Service.ServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.example.JobPortal.Dto.ApplicationDto;
import project.example.JobPortal.Entity.Application;
import project.example.JobPortal.Entity.Job;
import project.example.JobPortal.Entity.JobSeeker;
import project.example.JobPortal.Helper.ResourceNotFound;
import project.example.JobPortal.Repository.ApplicationRepository;
import project.example.JobPortal.Repository.JobRepository;
import project.example.JobPortal.Repository.JobSeekerRepository;
import project.example.JobPortal.Service.ApplicationService;



@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final JobSeekerRepository jobSeekerRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobRepository jobRepository,
                                  JobSeekerRepository jobSeekerRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.jobSeekerRepository = jobSeekerRepository;
    }

    @Override
    public ApplicationDto createApplication(ApplicationDto applicationDto, Long jobId, Long jobSeekerId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFound("Job not found"));

        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new ResourceNotFound("Job Seeker not found"));

        Application application = new Application();

        application.setAppliedDate(applicationDto.getAppliedDate());
        application.setStatus(applicationDto.getStatus());
        application.setJob(job);
        application.setJobSeeker(jobSeeker);

        Application savedApplication = applicationRepository.save(application);

        return convertToDto(savedApplication);
    }

    @Override
    public Page<ApplicationDto> getAllApplications(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("applicationId").ascending());

        Page<Application> applications = this.applicationRepository.findAll(pageable);

        return applications.map(this::convertToDto);
    }

    @Override
    public ApplicationDto getApplicationById(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFound("Application not found"));

        return convertToDto(application);
    }

    @Override
    public ApplicationDto updateApplication(Long applicationId,
                                            ApplicationDto applicationDto) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFound("Application not found"));

        application.setAppliedDate(applicationDto.getAppliedDate());
        application.setStatus(applicationDto.getStatus());

        Application updatedApplication = applicationRepository.save(application);

        return convertToDto(updatedApplication);
    }

    @Override
    public void deleteApplication(Long applicationId) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFound("Application not found"));

        applicationRepository.delete(application);
    }

    @Override
    public Page<ApplicationDto> getApplicationsByJob(Long jobId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("job.postedDate").ascending());

        Page<Application> applications = this.applicationRepository.findByJob_JobId(jobId, pageable);

        return applications.map(this::convertToDto);
    }

    @Override
    public Page<ApplicationDto> getApplicationsByJobSeeker(Long jobSeekerId, int page, int size) {

        Pageable pageable = PageRequest.of(page,size, Sort.by("appliedDate").ascending());

        Page<Application> applications = this.applicationRepository.findByJobSeeker_JobSeekerId(jobSeekerId, pageable);

        return applications.map(this::convertToDto);
    }

    private ApplicationDto convertToDto(Application application) {

        ApplicationDto dto = new ApplicationDto();

        dto.setApplicationId(application.getApplicationId());
        dto.setAppliedDate(application.getAppliedDate());
        dto.setStatus(application.getStatus());

        dto.setJobId(application.getJob().getJobId());
        dto.setJobSeekerId(application.getJobSeeker().getJobSeekerId());

        return dto;
    }
}
