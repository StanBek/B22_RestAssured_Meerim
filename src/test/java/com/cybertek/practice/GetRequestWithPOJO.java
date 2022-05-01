package com.cybertek.practice;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.HomeWorkAugust23.StudentInformation;
import com.cybertek.pojo.HomeWorkAugust23.Students;
import com.cybertek.pojo.Link;
import com.cybertek.pojo.Region;
import com.cybertek.pojo.Regions;
import com.cybertek.utilities.HRTestBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetRequestWithPOJO extends StudentInformation{
    @BeforeAll
    public static void init(){
        baseURI="http://api.cybertektraining.com";

    }
    @DisplayName("Get Student ID")
    @Test
    /**
     * Re do following homework with pojo. Use Jackson annotations if needed.
     *    send a get request to student id 23401 as a path parameter and accept header application/json
     *    verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
     *    verify Date header exists
     *    assert that
     *                 firstName Vera
     *                 batch 14
     *                 section 12
     *                 emailAddress aaa@gmail.com
     *                 companyName Cybertek
     *                 state IL
     *                 zipCode 60606
     */
    public void test1(){
        StudentInformation studentInformation= given().accept(ContentType.JSON)
                .and().pathParam("id", 21602)
                .when().get("/student/{id}")
                .then().statusCode(200)
                .log().all()
                .extract().jsonPath().getObject("students[0]",StudentInformation.class);
        System.out.println(studentInformation.toString());

       // System.out.println("studentInformation.getFirstName() = " + students.toString());

        assertThat(studentInformation.getFirstName(),is("Vera"));
        assertThat(studentInformation.getBatch(),is(14));
        assertThat(studentInformation.getContact().getEmailAddress(),is("aaa@gmail.com"));
        assertThat(studentInformation.getCompany().getCompanyName(),is("Cybertek"));
        assertThat(studentInformation.getLastName(),is("Jakson"));
        assertThat(studentInformation.getCompany().getAddress().getState(),is("IL"));
        assertThat(studentInformation.getCompany().getAddress().getZipCode(),is(60606));
    }
}
