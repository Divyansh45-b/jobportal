package com.JobPortal.JobPortal.Controllers;

import com.JobPortal.JobPortal.Helper.JobSeekerDto;
import com.JobPortal.JobPortal.Services.JobSeekerImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobSeeker")
public class JobSeekerController {

    private final JobSeekerImpl jobSeeker;

    public JobSeekerController (JobSeekerImpl jobSeeker)
    {
        this.jobSeeker = jobSeeker;
    }


    @PostMapping("/create")
    public ResponseEntity<JobSeekerDto> createJobSeeker
            (@RequestBody JobSeekerDto jobSeekerDto)
    {
        JobSeekerDto created = this.jobSeeker.createJobSeeker(jobSeekerDto);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update/{seekerId}")
    public ResponseEntity<JobSeekerDto> updateJobSeeker(@RequestBody JobSeekerDto jobSeekerDto, @PathVariable Integer seekerId)
    {
        JobSeekerDto updated = this.jobSeeker.updateJobSeeker(jobSeekerDto, seekerId);

        return ResponseEntity.ok(updated);
    }

    @GetMapping("/getById/{seekerId}")
    public ResponseEntity<JobSeekerDto> getById(@PathVariable Integer seekerId)
    {
        JobSeekerDto jobSeekerDto = this.jobSeeker.getJobSeekerById(seekerId);

        return ResponseEntity.ok(jobSeekerDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobSeekerDto>> getAll()
    {
        List<JobSeekerDto> all = this.jobSeeker.getAllJobSeekers();

        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/delete/{seekerId}")
    public ResponseEntity<String> delete(@PathVariable Integer seekerId)
    {
        String message = this.jobSeeker.deleteJobSeeker(seekerId);

        return ResponseEntity.ok(message);
    }
}
