package project.example.JobPortal.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.example.JobPortal.Dto.JobDto;
import project.example.JobPortal.Service.JobService;

import java.util.List;

@RestController
@RequestMapping("/job/api/")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping("/create/{companyId}/{recruiterId}")
    public JobDto createJob(@RequestBody JobDto jobDto,
                            @PathVariable Long companyId,
                            @PathVariable Long recruiterId) {

        return jobService.createJob(jobDto, companyId, recruiterId);
    }


    @GetMapping("/getAllJobs")
    public Page<JobDto> getAllJobs(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "3") int size) {
        return jobService.getAllJobs(page, size);
    }


    @GetMapping("/getJobById/{jobId}")
    public JobDto getJobById(@PathVariable Long jobId) {
        return jobService.getJobById(jobId);
    }


    @PutMapping("/updateJob/{jobId}")
    public JobDto updateJob(@PathVariable Long jobId,
                            @RequestBody JobDto jobDto) {

        return jobService.updateJob(jobId, jobDto);
    }

    // Delete Job
    @DeleteMapping("/deleteJobById/{jobId}")
    public String deleteJob(@PathVariable Long jobId) {

        jobService.deleteJob(jobId);
        return "Job deleted successfully";
    }

    // Get Jobs By Company
    @GetMapping("/getJobByCompany/{companyId}")
    public List<JobDto> getJobsByCompany(@PathVariable Long companyId) {

        return jobService.getJobsByCompany(companyId);
    }

    // Get Jobs By Recruiter
    @GetMapping("/getJobByRecruiter/{recruiterId}")
    public List<JobDto> getJobsByRecruiter(@PathVariable Long recruiterId) {

        return jobService.getJobsByRecruiter(recruiterId);
    }

}
