package com.JobPortal.JobPortal.Services;

import com.JobPortal.JobPortal.Enums.ApplicationStatus;
import com.JobPortal.JobPortal.Helper.ApplicationDto;

import java.util.List;

public interface ApplicationImpl {

    ApplicationDto applyJob(ApplicationDto applicationDto);

    List<ApplicationDto> getAllApplications();

    String deleteApplicationById(Integer applicationId);

    ApplicationDto updateApplication(Integer applicationId, ApplicationStatus applicationStatus);

    ApplicationDto getApplicationById (Integer applicationId);

    List<ApplicationDto> getApplicationsByJobId(Integer jobId);

    List<ApplicationDto> getApplicationBySeekerId(Integer seekerId);
}
