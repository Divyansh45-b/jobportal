package com.JobPortal.JobPortal.Services.ServiceImpl;

import com.JobPortal.JobPortal.Entities.Company;
import com.JobPortal.JobPortal.Entities.Job;
import com.JobPortal.JobPortal.Entities.Recruiter;
import com.JobPortal.JobPortal.Helper.JobDto;
import com.JobPortal.JobPortal.Repositories.CompanyRepo;
import com.JobPortal.JobPortal.Repositories.JobRepo;
import com.JobPortal.JobPortal.Repositories.RecruiterRepo;
import com.JobPortal.JobPortal.Services.JobImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class JobServiceImpl implements JobImpl {

    private final JobRepo jobRepo;
    private final ModelMapper modelMapper;
    private final CompanyRepo companyRepo;
    private final RecruiterRepo recruiterRepo;


    public JobServiceImpl(JobRepo jobRepo, ModelMapper modelMapper, CompanyRepo companyRepo, RecruiterRepo recruiterRepo)
    {
        this.jobRepo = jobRepo;
        this.modelMapper = modelMapper;
        this.companyRepo  = companyRepo;
        this.recruiterRepo= recruiterRepo;
    }

    @Override
    public JobDto create(JobDto jobDto) {

       Job job = new Job();

       job.setJobType(jobDto.getJobType());
       job.setJobDescription(jobDto.getJobDescription());
       job.setJobTitle(jobDto.getJobTitle());
       job.setSalary(jobDto.getSalary());
       job.setExperienceRequired(jobDto.getExperienceRequired());
       job.setLastDateToApply(jobDto.getLastDateToApply());

        Company company = this.companyRepo.findById(jobDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("company not found"));

        job.setCompany(company);

        Recruiter recruiter = this.recruiterRepo.findById(jobDto.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("recruiter not found"));
        job.setRecruiter(recruiter);

        Job saved =  this.jobRepo.save(job);
        return this.modelMapper.map(saved, JobDto.class);

    }

    @Override
    public List<JobDto> getAllJobs() {

        List<Job> jobs = this.jobRepo.findAll();

      return jobs.stream()
              .map(job -> this.modelMapper.map(job, JobDto.class))
              .toList();
    }

    @Override
    public String delete(Integer jobId) {

      Job job = this.jobRepo.findById(jobId).orElseThrow(()-> new RuntimeException("job not found with id :"+ jobId));
      this.jobRepo.delete(job);
      return "job deleted successfully";
    }

    @Override
    public JobDto update(JobDto jobDto, Integer jobId) {

        Job job = this.jobRepo.findById(jobId)
                .orElseThrow(()-> new RuntimeException("jobId not found to update"));

        job.setJobTitle(jobDto.getJobTitle());
        job.setJobDescription(jobDto.getJobDescription());
        job.setJobType(jobDto.getJobType());
        job.setSalary(jobDto.getSalary());
        job.setExperienceRequired(jobDto.getExperienceRequired());
        job.setLastDateToApply(jobDto.getLastDateToApply());

        Company company1 =  this.companyRepo.findById(jobDto.getCompanyId())
                .orElseThrow(()-> new RuntimeException("companyId not found"));

        job.setCompany(company1);

        Recruiter recruiter = this.recruiterRepo.findById(jobDto.getRecruiterId())
                .orElseThrow(() -> new RuntimeException("recruiter not found"));
        job.setRecruiter(recruiter);

        Job savedJob = this.jobRepo.save(job);

        return this.modelMapper.map(savedJob, JobDto.class);
    }

    @Override
    public List<JobDto> findJobByName(String jobTitle) {

        List<Job> jobs = this.jobRepo.findJobByJobTitleContainingIgnoreCase(jobTitle);

        return jobs.stream()
                .map(job-> this.modelMapper.map(job, JobDto.class))
                .toList();
    }
}
