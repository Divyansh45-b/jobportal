package com.JobPortal.JobPortal.Services;


import com.JobPortal.JobPortal.Helper.JobDto;

import java.util.List;

public interface JobImpl {

        JobDto create(JobDto jobDto);

        List<JobDto> getAllJobs();

        String delete(Integer jobId);

        JobDto update(JobDto jobDto, Integer jobId);

        List<JobDto> findJobByName(String keyword);
}
