package project.example.JobPortal.Service;

import org.springframework.data.domain.Page;
import project.example.JobPortal.Dto.RecruiterDto;
import java.util.List;

public interface RecruiterService {

    // crud operations
    RecruiterDto create(RecruiterDto recruiterDto, Long companyId);

    Page<RecruiterDto> getAllRecruiter(int page , int size);

    void deleteRecruiter(Long recruiterId);

    RecruiterDto updateRecruiter(Long recruiterId, RecruiterDto recruiterDto);

    // extra operations
    RecruiterDto getRecruiterById(Long recruiterId);


}
