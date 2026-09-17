package com.JobPortal.JobPortal.Controllers;

import com.JobPortal.JobPortal.Helper.JobDto;
import com.JobPortal.JobPortal.Helper.RecruiterDto;
import com.JobPortal.JobPortal.Services.RecruiterImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {



    private final RecruiterImpl recruiter;

    public RecruiterController(RecruiterImpl recruiter)
    {
        this.recruiter = recruiter;
    }

    @PostMapping("/create")
    public ResponseEntity<RecruiterDto> createRecruiter(@RequestBody RecruiterDto recruiterDto)
    {
        RecruiterDto recruiter1 = this.recruiter.createRecruiter(recruiterDto);

        return new ResponseEntity<>(recruiter1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{recruiterId}")
    public ResponseEntity<String> deleteRecruiter(@PathVariable Integer recruiterId)
    {
        String deleted = this.recruiter.deleteById(recruiterId);

        return new ResponseEntity<>(deleted,HttpStatus.OK);
    }

    @PutMapping("/update/{recruiterId}")
    public ResponseEntity<RecruiterDto> updateRecruiter(@RequestBody RecruiterDto recruiterDto,
                                                        @PathVariable Integer recruiterId)
    {
        RecruiterDto recruiter1 = this.recruiter
                .updateRecruiter(recruiterDto,recruiterId);

        return new ResponseEntity<>(recruiter1,HttpStatus.OK);
    }

    @GetMapping("/get/{recruiterId}")
    public ResponseEntity<RecruiterDto> getRecruiterById(@PathVariable Integer recruiterId)
    {
        RecruiterDto recruiter1 = this.recruiter
                .getRecruiterById(recruiterId);

        return new ResponseEntity<>(recruiter1,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RecruiterDto>> getAllRecruiter()
    {
        List<RecruiterDto> allRecruiter = this.recruiter.getAllRecruiter();

        return new ResponseEntity<>(allRecruiter,HttpStatus.OK);
    }

    @GetMapping("/{recruiterId}/jobs")
    public ResponseEntity<List<JobDto>> getJobsPostedByRecruiter( @PathVariable Integer recruiterId)
    {
        List<JobDto> jobs = this.recruiter
                .getJobsPostedByRecruiter(recruiterId);

        return new ResponseEntity<>(jobs,HttpStatus.OK);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<RecruiterDto>> getRecruitersByCompany(@PathVariable Integer companyId)
    {
        List<RecruiterDto> recruiters = this.recruiter
                .getRecruitersByCompany(companyId);

        return new ResponseEntity<>(recruiters, HttpStatus.OK);
    }
}
