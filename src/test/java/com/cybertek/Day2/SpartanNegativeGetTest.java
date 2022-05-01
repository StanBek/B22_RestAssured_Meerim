package com.cybertek.Day2;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
public class SpartanNegativeGetTest {
    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://34.207.129.114:8000";
    }
        /**
    Given Accept type application/xml
    When user send GET request to api/spartans end point
    Then status code must be 406
    And response Content Type must be application/xml
    */
        @DisplayName("Get request to /api/spartans/10")
        @Test
    public void test1(){
            Response response = given().accept(ContentType.XML)
                    .when()
                    .get( "/api/spartans/10");
            System.out.println(response.statusCode());
            assertEquals(406,response.statusCode());

            assertEquals("application/xml;charset=UTF-8",response.contentType());
            response.prettyPrint();

        }
    }

