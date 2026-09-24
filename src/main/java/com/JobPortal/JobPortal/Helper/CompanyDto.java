package com.JobPortal.JobPortal.Helper;


import lombok.Data;

import java.time.LocalDate;

@Data
public class CompanyDto {



    private String companyName;

    private String companyDescription;

    private String location;

    private String website;

    private LocalDate createdAt;

}
