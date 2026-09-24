package project.example.JobPortal.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
        import project.example.JobPortal.Dto.JobSeekerDto;
import project.example.JobPortal.Service.JobSeekerService;



@RestController
@RequestMapping("/jobSeeker/api/")
public class JobSeekerController {

    private final JobSeekerService jobSeekerService;

    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @PostMapping("/create")
    public JobSeekerDto createJobSeeker(@RequestBody JobSeekerDto jobSeekerDto) {

        return jobSeekerService.createJobSeeker(jobSeekerDto);
    }

    @GetMapping("/getAllJobSeekers")
    public Page<JobSeekerDto> getAllJobSeekers(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam (defaultValue ="3" )int  size) {

        return jobSeekerService.getAllJobSeekers(page, size);
    }


    @PutMapping("/updateJobSeeker/{jobSeekerId}")
    public JobSeekerDto updateJobSeeker(
            @PathVariable Long jobSeekerId,
            @RequestBody JobSeekerDto jobSeekerDto) {

        return jobSeekerService.updateJobSeeker(
                jobSeekerId, jobSeekerDto);
    }

    @DeleteMapping("/deleteJobSeekerById/{jobSeekerId}")
    public String deleteJobSeeker(
            @PathVariable Long jobSeekerId) {

        jobSeekerService.deleteJobSeeker(jobSeekerId);

        return "Job Seeker deleted successfully";
    }
}