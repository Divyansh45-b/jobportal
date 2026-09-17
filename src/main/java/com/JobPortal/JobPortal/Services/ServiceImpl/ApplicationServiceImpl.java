package com.JobPortal.JobPortal.Services.ServiceImpl;

import com.JobPortal.JobPortal.Entities.Application;
import com.JobPortal.JobPortal.Entities.Job;
import com.JobPortal.JobPortal.Entities.JobSeeker;
import com.JobPortal.JobPortal.Enums.ApplicationStatus;
import com.JobPortal.JobPortal.Helper.ApplicationDto;
import com.JobPortal.JobPortal.Repositories.ApplicationRepo;
import com.JobPortal.JobPortal.Repositories.JobRepo;
import com.JobPortal.JobPortal.Repositories.JobSeekerRepo;
import com.JobPortal.JobPortal.Services.ApplicationImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ApplicationServiceImpl implements ApplicationImpl {

    private final ApplicationRepo applicationRepo;
    private final ModelMapper modelMapper;
    private  final JobRepo jobRepo;
    private  final JobSeekerRepo jobSeekerRepo;

    public ApplicationServiceImpl(ApplicationRepo applicationRepo, ModelMapper modelMapper,
                                  JobRepo jobRepo, JobSeekerRepo jobSeekerRepo)
    {
        this.applicationRepo = applicationRepo;
        this.modelMapper = modelMapper;
        this.jobRepo = jobRepo;
        this.jobSeekerRepo = jobSeekerRepo;
    }

    @Override
    public ApplicationDto applyJob(ApplicationDto applicationDto) {

       Job job = this.jobRepo.findById(applicationDto.getJobId())
               .orElseThrow(()->new RuntimeException("jobId not found"));

       JobSeeker jobSeeker = this.jobSeekerRepo.findById(applicationDto.getSeekerId())
                .orElseThrow(()-> new RuntimeException("seekerId not found"));

       Application application =  new Application();

       application.setJob(job);
       application.setJobSeeker(jobSeeker);
       application.setStatus(ApplicationStatus.APPLIED);

       Application savedApplication = this.applicationRepo.save(application);

       return this.modelMapper.map(savedApplication, ApplicationDto.class);
    }

    @Override
    public List<ApplicationDto> getAllApplications() {

        List<Application> applicationList =  this.applicationRepo.findAll();

       return applicationList.stream()
               .map(application -> this.modelMapper.map(application, ApplicationDto.class))
               .toList();
    }

    @Override
    public String deleteApplicationById(Integer applicationId) {

        Application application = this.applicationRepo.findById(applicationId)
                .orElseThrow(()-> new RuntimeException("id not found "));

        this.applicationRepo.delete(application);
        return "application deleted successfully";
    }

    @Override
    public ApplicationDto updateApplication(Integer applicationId, ApplicationStatus applicationStatus)
    {
        Application application = this.applicationRepo.findById(applicationId)
                                       .orElseThrow(()-> new RuntimeException("applicationId not found"));

        application.setStatus(applicationStatus);

        Application savedApplication = this.applicationRepo.save(application);

        return this.modelMapper.map(savedApplication, ApplicationDto.class);
    }

    @Override
    public ApplicationDto getApplicationById(Integer applicationId) {

        Application application = this.applicationRepo.findById(applicationId)
                                    .orElseThrow(()-> new RuntimeException("applicationId not found"));

        return this.modelMapper.map(application, ApplicationDto.class);
    }

    @Override
    public List<ApplicationDto> getApplicationsByJobId(Integer jobId) {

        List<Application> applicationList = this.applicationRepo.findAllByJob_JobId(jobId);

         return applicationList.stream()
                 .map(application -> this.modelMapper.map(application, ApplicationDto.class))
                 .toList();

    }

    @Override
    public List<ApplicationDto> getApplicationBySeekerId(Integer seekerId) {

        List<Application> applicationList =  this.applicationRepo.findAllByJobSeeker_SeekerId(seekerId);

        /// used streams.
        return applicationList
                .stream()
                .map(application -> this.modelMapper.map(application, ApplicationDto.class))
                .toList();
    }

}
