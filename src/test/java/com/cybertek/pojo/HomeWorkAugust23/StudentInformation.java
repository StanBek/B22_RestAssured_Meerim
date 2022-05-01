package com.cybertek.pojo.HomeWorkAugust23;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentInformation {
    private String firstName;
    private String lastName;
    private int batch;
    private ContactInformation contact;
    private CompanyInformation company;


}
