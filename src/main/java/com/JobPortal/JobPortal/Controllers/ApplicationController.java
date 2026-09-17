package com.JobPortal.JobPortal.Controllers;


import com.JobPortal.JobPortal.Enums.ApplicationStatus;
import com.JobPortal.JobPortal.Helper.ApplicationDto;
import com.JobPortal.JobPortal.Services.ApplicationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {


    private final ApplicationImpl applicationImpl;

    public ApplicationController (ApplicationImpl applicationImpl)
    {
        this.applicationImpl = applicationImpl;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicationDto> applyJob(@RequestBody ApplicationDto applicationDto) {

        ApplicationDto applied = this.applicationImpl.applyJob(applicationDto);

        return new ResponseEntity<>(applied, HttpStatus.CREATED);
    }

    @GetMapping("/getAllApplications")
    public ResponseEntity<List<ApplicationDto>> getAllApplications() {

        return ResponseEntity.ok(this.applicationImpl.getAllApplications());
    }

    @DeleteMapping("/delete/{applicationId}")
    public ResponseEntity<String> deleteApplication(@PathVariable Integer applicationId) {

        return ResponseEntity.ok(this.applicationImpl.deleteApplicationById(applicationId));
    }

    @PutMapping("/update/{application}")
    public ResponseEntity<ApplicationDto> update(@PathVariable Integer applicationId,@RequestParam ApplicationStatus status)
    {
        ApplicationDto applicationDto = this.applicationImpl.updateApplication(applicationId,status);

        return ResponseEntity.ok(applicationDto);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationDto> getApplicationById(
            @PathVariable Integer applicationId) {

        ApplicationDto applicationDto =
                this.applicationImpl.getApplicationById(applicationId);

        return ResponseEntity.ok(applicationDto);
    }


    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByJobId(
            @PathVariable Integer jobId) {

        List<ApplicationDto> applications =
                this.applicationImpl.getApplicationsByJobId(jobId);

        return ResponseEntity.ok(applications);
    }

    @GetMapping("/seeker/{seekerId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsBySeekerId(
            @PathVariable Integer seekerId) {

        List<ApplicationDto> applications =
                this.applicationImpl.getApplicationBySeekerId(seekerId);

        return ResponseEntity.ok(applications);
    }
}
