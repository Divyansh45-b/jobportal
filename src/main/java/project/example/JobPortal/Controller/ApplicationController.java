package project.example.JobPortal.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.example.JobPortal.Dto.ApplicationDto;
import project.example.JobPortal.Service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/application/api/")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/create/{jobId}/{jobSeekerId}")
    public ApplicationDto createApplication(
            @RequestBody ApplicationDto applicationDto,
            @PathVariable Long jobId,
            @PathVariable Long jobSeekerId) {

        return applicationService.createApplication(
                applicationDto, jobId, jobSeekerId);
    }

    @GetMapping("/getAllApplications")
    public Page<ApplicationDto> getAllApplications(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int size) {

        return applicationService.getAllApplications(page, size);
    }

    @GetMapping("/getApplicationById/{applicationId}")
    public ApplicationDto getApplicationById(
            @PathVariable Long applicationId) {

        return applicationService.getApplicationById(applicationId);
    }

    @PutMapping("/updateApplication/{applicationId}")
    public ApplicationDto updateApplication(
            @PathVariable Long applicationId,
            @RequestBody ApplicationDto applicationDto) {

        return applicationService.updateApplication(
                applicationId, applicationDto);
    }

    @DeleteMapping("/deleteApplicationById/{applicationId}")
    public String deleteApplication(
            @PathVariable Long applicationId) {

        applicationService.deleteApplication(applicationId);

        return "Application deleted successfully";
    }

    @GetMapping("/getApplicationsByJob/{jobId}")
    public Page<ApplicationDto> getApplicationsByJob(@PathVariable Long jobId,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "3") int size) {

        return applicationService.getApplicationsByJob(jobId, page, size);
    }

    @GetMapping("/getApplicationsByJobSeeker/{jobSeekerId}")
    public Page<ApplicationDto> getApplicationsByJobSeeker(@PathVariable Long jobSeekerId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "3") int size) {

        return this.applicationService.getApplicationsByJobSeeker(jobSeekerId, page, size);
    }
}