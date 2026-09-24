package project.example.JobPortal.Controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import project.example.JobPortal.Dto.RecruiterDto;
import project.example.JobPortal.Service.RecruiterService;

import java.util.List;

@RestController
@RequestMapping("/recruiter/api/")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService)
    {
        this.recruiterService = recruiterService;
    }

    @PostMapping("/create/{companyId}")
    public RecruiterDto createRecruiter(@RequestBody RecruiterDto recruiterDto, @PathVariable Long companyId)
    {
        return this.recruiterService.create(recruiterDto, companyId);
    }

    @GetMapping("/getAllRecruiters")
    public Page<RecruiterDto> getAllRecruiters(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "3") int size) {
        return recruiterService.getAllRecruiter(page, size);
    }


    @GetMapping("/getRecruiterById/{recruiterId}")
    public RecruiterDto getRecruiterById(@PathVariable Long recruiterId) {
        return recruiterService.getRecruiterById(recruiterId);
    }

    @PutMapping("/updateRecruiter/{recruiterId}")
    public RecruiterDto updateRecruiter(@PathVariable Long recruiterId,
                                        @RequestBody RecruiterDto recruiterDto) {
        return recruiterService.updateRecruiter(recruiterId, recruiterDto);
    }

    @DeleteMapping("/deleteRecruiterById/{recruiterId}")
    public String deleteRecruiter(@PathVariable Long recruiterId) {
        recruiterService.deleteRecruiter(recruiterId);
        return "Recruiter deleted successfully";
    }
}
