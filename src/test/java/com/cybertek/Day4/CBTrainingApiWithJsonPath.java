package com.cybertek.Day4;

import com.cybertek.utilities.HRTestBase;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class CBTrainingApiWithJsonPath {
    @BeforeAll
    public static void init(){
        //save baseurl inside this varibale so that we dont need to type each http  method
        RestAssured.baseURI="http://api.cybertektraining.com";

    }
    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code /content type /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /**
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 21602)
                .when().get("/student/{id}");
        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertEquals("gzip",response.header("Content-Encoding"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        JsonPath jsonPath=response.jsonPath();
        String firstName=jsonPath.getString("students[0].firstName");
        System.out.println(firstName.toString());
        int batch=jsonPath.getInt("students[0].batch");
        int section=jsonPath.getInt("students[0].section");
        String emailAddress=jsonPath.getString("students[0].contact.emailAddress");
        String companyName=jsonPath.getString("students[0].company.companyName");
        System.out.println(companyName);
        String state=jsonPath.getString("students[0].company.address.state");
        int zipCode=jsonPath.getInt("students[0].company.address.zipCode");
        assertEquals("Vera",firstName);
        assertEquals(14,batch);
        assertEquals(12,section);
        assertEquals("aaa@gmail.com",emailAddress);
        assertEquals("Cybertek",companyName);
        assertEquals("IL",state);
        assertEquals(60606,zipCode);
    }
}
