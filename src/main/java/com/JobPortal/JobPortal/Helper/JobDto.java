package com.JobPortal.JobPortal.Helper;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JobDto {

    private String jobTitle;

    private String jobDescription;

    private String experienceRequired;

    private Double salary;

    private String jobType;

    private LocalDate createdAt;

    private LocalDate lastDateToApply;

    //isko hamesha set krna padhega..
    private Integer companyId;


    private Integer recruiterId;
}
