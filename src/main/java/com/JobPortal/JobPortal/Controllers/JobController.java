package com.JobPortal.JobPortal.Controllers;

import com.JobPortal.JobPortal.Helper.JobDto;
import com.JobPortal.JobPortal.Services.JobImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/job")
public class JobController {

    private final JobImpl jobImpl;

    public JobController (JobImpl jobImpl)
    {
        this.jobImpl = jobImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<JobDto> createJob(@RequestBody JobDto jobDto)
    {
        JobDto jobDto1 = this.jobImpl.create(jobDto);
        return new ResponseEntity<>(jobDto1, HttpStatus.CREATED);
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<List<JobDto>> getAllJobs()
    {
        List<JobDto> jobDtoList  = this.jobImpl.getAllJobs();

        return ResponseEntity.ok(jobDtoList);
    }

    @PutMapping("/updateJob/{jobId}")
    public ResponseEntity<JobDto> updateJob(@RequestBody JobDto jobDto,@PathVariable Integer jobId)
    {
        JobDto updatedJob = this.jobImpl.update(jobDto, jobId);
      return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/deleteJobById/{jobId}")
    public ResponseEntity<String> deleteJobById (@PathVariable Integer jobId)
    {

        String message = this.jobImpl.delete(jobId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getJobByTitle/{keyword}")
    public ResponseEntity<List<JobDto>> jobByTitle(@PathVariable  String keyword)
    {
        List<JobDto> byTitle = this.jobImpl.findJobByName(keyword);
        return ResponseEntity.ok(byTitle);
    }
}
