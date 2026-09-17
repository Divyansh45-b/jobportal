package com.JobPortal.JobPortal.Services.ServiceImpl;

import com.JobPortal.JobPortal.Entities.JobSeeker;
import com.JobPortal.JobPortal.Helper.JobSeekerDto;
import com.JobPortal.JobPortal.Repositories.JobSeekerRepo;
import com.JobPortal.JobPortal.Services.JobSeekerImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class JobSeekerServiceImpl implements JobSeekerImpl {

    private final JobSeekerRepo jobSeekerRepo;

    private final ModelMapper modelMapper;

    public JobSeekerServiceImpl(JobSeekerRepo jobSeekerRepo, ModelMapper modelMapper)
    {
        this.jobSeekerRepo = jobSeekerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobSeekerDto createJobSeeker(JobSeekerDto jobSeekerDto) {

        JobSeeker jobSeeker = this.modelMapper.map(jobSeekerDto, JobSeeker.class);

        JobSeeker saved = this.jobSeekerRepo.save(jobSeeker);

        return this.modelMapper.map(saved, JobSeekerDto.class);
    }

    @Override
    public JobSeekerDto updateJobSeeker(JobSeekerDto jobSeekerDto, Integer seekerId)
    {

        JobSeeker jobSeeker = this.jobSeekerRepo.findById(seekerId)
                .orElseThrow(() -> new RuntimeException("id not found"));

        jobSeeker.setFullName(jobSeekerDto.getFullName());
        jobSeeker.setEmail(jobSeekerDto.getEmail());
        jobSeeker.setContactNumber(jobSeekerDto.getContactNumber());
        jobSeeker.setSkills(jobSeekerDto.getSkills());
        jobSeeker.setEducation(jobSeekerDto.getEducation());
        jobSeeker.setExperience(jobSeekerDto.getExperience());

        JobSeeker saved = this.jobSeekerRepo.save(jobSeeker);

        return this.modelMapper.map(saved, JobSeekerDto.class);
    }

    @Override
    public JobSeekerDto getJobSeekerById(Integer seekerId) {

        JobSeeker jobSeeker = this.jobSeekerRepo.findById(seekerId)
                .orElseThrow(() -> new RuntimeException("id not found"));

        return this.modelMapper.map(jobSeeker, JobSeekerDto.class);
    }

    @Override
    public List<JobSeekerDto> getAllJobSeekers() {

        List<JobSeeker> jobs  = this.jobSeekerRepo.findAll();

        return jobs.stream()
                .map(jobSeeker -> this.modelMapper.map(jobSeeker, JobSeekerDto.class))
                .toList();

    }

    @Override
    public String deleteJobSeeker(Integer seekerId) {

        JobSeeker jobSeeker = this.jobSeekerRepo.findById(seekerId)
                .orElseThrow(() -> new RuntimeException("id not found"));

        this.jobSeekerRepo.delete(jobSeeker);

        return "deleted successfully";
    }
}
