package project.example.JobPortal.Service.ServiceImpl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.example.JobPortal.Dto.JobDto;
import project.example.JobPortal.Entity.Company;
import project.example.JobPortal.Entity.Job;
import project.example.JobPortal.Entity.Recruiter;
import project.example.JobPortal.Repository.CompanyRepository;
import project.example.JobPortal.Repository.JobRepository;
import project.example.JobPortal.Repository.RecruiterRepository;
import project.example.JobPortal.Service.JobService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final RecruiterRepository recruiterRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository, RecruiterRepository recruiterRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public JobDto createJob(JobDto jobDto, Long companyId, Long recruiterId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        Job job = new Job();

        job.setJobTitle(jobDto.getJobTitle());
        job.setLocation(jobDto.getLocation());
        job.setDescription(jobDto.getDescription());
        job.setExperience(jobDto.getExperience());
        job.setSkills(jobDto.getSkills());
        job.setVacancies(jobDto.getVacancies());
        job.setPostedDate(jobDto.getPostedDate());
        job.setLastDateToApply(jobDto.getLastDateToApply());

        job.setCompany(company);
        job.setRecruiter(recruiter);

        Job savedJob = jobRepository.save(job);

        return convertToDto(savedJob);
    }

    @Override
    public Page<JobDto> getAllJobs(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("jobId").ascending());

        Page<Job> jobs = this.jobRepository.findAll(pageable);

        return jobs.map(this::convertToDto);
    }

    @Override
    public JobDto getJobById(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        return convertToDto(job);
    }

    @Override
    public JobDto updateJob(Long jobId, JobDto jobDto) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setJobTitle(jobDto.getJobTitle());
        job.setLocation(jobDto.getLocation());
        job.setDescription(jobDto.getDescription());
        job.setExperience(jobDto.getExperience());
        job.setSkills(jobDto.getSkills());
        job.setVacancies(jobDto.getVacancies());
        job.setPostedDate(jobDto.getPostedDate());
        job.setLastDateToApply(jobDto.getLastDateToApply());

        Job updatedJob = jobRepository.save(job);

        return convertToDto(updatedJob);
    }

    @Override
    public void deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        jobRepository.delete(job);
    }

    @Override
    public List<JobDto> getJobsByCompany(Long companyId) {

        return jobRepository.findAll()
                .stream()
                .filter(job ->
                        job.getCompany().getCompanyId().equals(companyId))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobDto> getJobsByRecruiter(Long recruiterId) {

        return jobRepository.findAll()
                .stream()
                .filter(job ->
                        job.getRecruiter().getRecruiterId().equals(recruiterId))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private JobDto convertToDto(Job job) {

        JobDto dto = new JobDto();

        dto.setJobId(job.getJobId());
        dto.setJobTitle(job.getJobTitle());
        dto.setLocation(job.getLocation());
        dto.setDescription(job.getDescription());
        dto.setExperience(job.getExperience());
        dto.setSkills(job.getSkills());
        dto.setVacancies(job.getVacancies());
        dto.setPostedDate(job.getPostedDate());
        dto.setLastDateToApply(job.getLastDateToApply());

        dto.setCompanyId(job.getCompany().getCompanyId());
        dto.setRecruiterId(job.getRecruiter().getRecruiterId());

        return dto;
    }
}