package com.JobPortal.JobPortal.Services;

import com.JobPortal.JobPortal.Helper.JobDto;
import com.JobPortal.JobPortal.Helper.RecruiterDto;

import java.util.List;

public interface RecruiterImpl {


    RecruiterDto createRecruiter(RecruiterDto recruiterDto);

    String deleteById(Integer recruiterId);

    RecruiterDto updateRecruiter(RecruiterDto recruiterDto, Integer recruiterId);

    List<RecruiterDto> getAllRecruiter();

    RecruiterDto getRecruiterById(Integer recruiterId);

    List<JobDto> getJobsPostedByRecruiter(Integer recruiterId);

    List<RecruiterDto> getRecruitersByCompany(Integer companyId);
}
