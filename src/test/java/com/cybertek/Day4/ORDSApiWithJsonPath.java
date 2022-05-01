package com.cybertek.Day4;

import com.cybertek.utilities.HRTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ORDSApiWithJsonPath  extends HRTestBase {
    @DisplayName("GET request to Countries")
    @Test
    public void test1(){

        Response response = get("/countries");

        //get the second country name with JsonPath

        //to use jsonpath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_id
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);

  //get all country names where their region id is equal to 2
        List<String> countryNameWithRegionId2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(countryNameWithRegionId2);

    }
    @DisplayName("GET request to /employees with query param")
    @Test

    public void test2(){
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");
        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath=response.jsonPath();
       List<String>employeeITProg= jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProg);
        List<String>employeeFirstName= jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.first_name");
        System.out.println(employeeFirstName);
        List<Integer>employeesWhoIsMaking10K=jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println("employeesWhoIsMaking10K = " + employeesWhoIsMaking10K);
        //get the max salary first_name
        String kingFirstName=jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);


        //students[0]company.address
    }

}
