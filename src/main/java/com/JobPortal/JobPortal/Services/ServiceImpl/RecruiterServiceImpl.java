package com.JobPortal.JobPortal.Services.ServiceImpl;

import com.JobPortal.JobPortal.Entities.Company;
import com.JobPortal.JobPortal.Entities.Job;
import com.JobPortal.JobPortal.Entities.Recruiter;
import com.JobPortal.JobPortal.Helper.JobDto;
import com.JobPortal.JobPortal.Helper.RecruiterDto;

import com.JobPortal.JobPortal.Repositories.CompanyRepo;
import com.JobPortal.JobPortal.Repositories.RecruiterRepo;
import com.JobPortal.JobPortal.Services.RecruiterImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RecruiterServiceImpl implements RecruiterImpl {

    private final RecruiterRepo recruiterRepo;

    private final ModelMapper modelMapper;

    private final CompanyRepo companyRepo;

    public RecruiterServiceImpl(RecruiterRepo recruiterRepo, ModelMapper modelMapper,CompanyRepo companyRepo)
    {
        this.modelMapper = modelMapper;
        this.recruiterRepo = recruiterRepo;
        this.companyRepo= companyRepo;
    }

    @Override
    public RecruiterDto createRecruiter(RecruiterDto recruiterDto) {

        Recruiter recruiter = new Recruiter();

        recruiter.setRecruiterName(recruiterDto.getRecruiterName());
        recruiter.setContactNumber(recruiterDto.getContactNumber());

        Company company = this.companyRepo.findById(recruiterDto.getCompanyId())
                .orElseThrow(() -> new RuntimeException("companyId not found"));

        recruiter.setCompany(company);

        Recruiter saved = this.recruiterRepo.save(recruiter);

        return this.modelMapper.map(saved, RecruiterDto.class);
    }

    @Override
    public String deleteById(Integer recruiterId) {

        Recruiter recruiter = this.recruiterRepo.findById(recruiterId)
                .orElseThrow(()-> new RuntimeException("id not found"));

        this.recruiterRepo.delete(recruiter);
        return "deleted successfully";
    }

    @Override
    public RecruiterDto updateRecruiter(RecruiterDto recruiterDto, Integer recruiterId) {

        Recruiter recruiter = this.recruiterRepo.findById(recruiterId)
                .orElseThrow(()-> new RuntimeException("id not found "));

        recruiter.setRecruiterName(recruiterDto.getRecruiterName());
        recruiter.setContactNumber(recruiterDto.getContactNumber());

        Recruiter saved = this.recruiterRepo.save(recruiter);

        return this.modelMapper.map(saved, RecruiterDto.class);

    }

    @Override
    public List<RecruiterDto> getAllRecruiter() {

        List<Recruiter> getAll = this.recruiterRepo.findAll();

        return getAll.stream()
                .map(recruiter -> this.modelMapper.map(recruiter, RecruiterDto.class))
                .toList();
    }

    @Override
    public RecruiterDto getRecruiterById(Integer recruiterId) {

        Recruiter recruiter = this.recruiterRepo.findById(recruiterId)
                .orElseThrow(()-> new RuntimeException("id not found "));


        return this.modelMapper.map(recruiter, RecruiterDto.class);
    }

    @Override
    public List<JobDto> getJobsPostedByRecruiter(Integer recruiterId) {

        Recruiter recruiter = this.recruiterRepo.findById(recruiterId)
                .orElseThrow(()-> new RuntimeException("id not found "));

        List<Job> jobs = recruiter.getJobs();

       return jobs.stream()
               .map(job -> this.modelMapper.map(job, JobDto.class))
               .toList();
    }

    @Override
    public List<RecruiterDto> getRecruitersByCompany(Integer companyId) {

        Company company = this.companyRepo.findById(companyId)
                .orElseThrow(()-> new RuntimeException("companyId not found"));

        List<Recruiter> recruiters = company.getRecruiters();

        return recruiters.stream()
                .map(recruiter -> this.modelMapper.map(recruiter, RecruiterDto.class))
                .toList();
    }
}
