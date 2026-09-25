package project.example.JobPortal.Service.ServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.example.JobPortal.Dto.RecruiterDto;
import project.example.JobPortal.Entity.Company;
import project.example.JobPortal.Entity.Recruiter;
import project.example.JobPortal.Helper.ResourceNotFound;
import project.example.JobPortal.Repository.CompanyRepository;
import project.example.JobPortal.Repository.RecruiterRepository;
import project.example.JobPortal.Service.RecruiterService;


@Service
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterRepository recruiterRepository;
    private final CompanyRepository companyRepository;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository,
                                CompanyRepository companyRepository) {
        this.recruiterRepository = recruiterRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public RecruiterDto create(RecruiterDto recruiterDto, Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFound("Company not found"));

        Recruiter recruiter = new Recruiter();

        recruiter.setRecruiterName(recruiterDto.getRecruiterName());
        recruiter.setEmail(recruiterDto.getEmail());
        recruiter.setCompany(company);

        Recruiter savedRecruiter = recruiterRepository.save(recruiter);

        return convertToDto(savedRecruiter);
    }

    @Override
    public Page<RecruiterDto> getAllRecruiter(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("recruiterId").ascending());

        Page<Recruiter> page1= this.recruiterRepository.findAll(pageable);
        return page1.map(this::convertToDto);
    }

    @Override
    public RecruiterDto getRecruiterById(Long recruiterId) {

        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFound("Recruiter not found"));

        return convertToDto(recruiter);
    }

    @Override
    public void deleteRecruiter(Long recruiterId) {

        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFound("Recruiter not found"));

        recruiterRepository.delete(recruiter);
    }

    @Override
    public RecruiterDto updateRecruiter(Long recruiterId,
                                        RecruiterDto recruiterDto) {

        Recruiter recruiter = recruiterRepository.findById(recruiterId)
                .orElseThrow(() -> new ResourceNotFound("Recruiter not found"));

        recruiter.setRecruiterName(recruiterDto.getRecruiterName());
        recruiter.setEmail(recruiterDto.getEmail());

        Recruiter updatedRecruiter = recruiterRepository.save(recruiter);

        return convertToDto(updatedRecruiter);
    }

    private RecruiterDto convertToDto(Recruiter recruiter) {

        RecruiterDto dto = new RecruiterDto();

        dto.setRecruiterId(recruiter.getRecruiterId());
        dto.setRecruiterName(recruiter.getRecruiterName());
        dto.setEmail(recruiter.getEmail());
        dto.setCompanyId(recruiter.getCompany().getCompanyId());

        return dto;
    }
}