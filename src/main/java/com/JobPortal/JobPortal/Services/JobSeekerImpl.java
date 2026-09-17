package com.JobPortal.JobPortal.Services;

import com.JobPortal.JobPortal.Helper.JobSeekerDto;

import java.util.List;

public interface JobSeekerImpl {

    JobSeekerDto createJobSeeker(JobSeekerDto jobSeekerDto);

    JobSeekerDto updateJobSeeker(JobSeekerDto jobSeekerDto, Integer seekerId);

    JobSeekerDto getJobSeekerById(Integer seekerId);

    List<JobSeekerDto> getAllJobSeekers();

    String deleteJobSeeker(Integer seekerId);
}
