package com.cybertek.practice;


import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

import com.cybertek.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ZipCodeHomeWork {
//    @BeforeAll
//    public static void init(){
//        baseURI="http://api/zippopotam.us";
//    }
    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     */

    @Test
    public void test1(){
        Response response=get("http://api.zippopotam.us/us/22031");
        System.out.println(response.path("'post code'").toString());
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertEquals("cloudflare",response.header("Server"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));
        JsonPath jsonPath=response.jsonPath();
        assertEquals(22031,response.jsonPath().getInt("'post code'"));
        assertEquals("United States",response.jsonPath().getString("'country'"));
        assertEquals("US",response.jsonPath().getString("'country abbreviation'"));
        assertEquals("Fairfax",response.jsonPath().getString("places[0].'place name'"));
        assertEquals("Virginia",response.jsonPath().getString("places[0].'state'"));
        assertEquals(38.8604,response.jsonPath().getDouble("places[0].'latitude'"));

    }
    @Test
    public void test2(){
        /**
         * Given Accept application/json
         * And path zipcode is 50000
         * When I send a GET request to /us endpoint
         * Then status code must be 404
         * And content type must be application/json
         */

        Response response=get("http://api.zippopotam.us/us/50000");
       // System.out.println(response.path("'post code'").toString());
        assertEquals(404,response.statusCode());
        assertEquals("application/json",response.contentType());
    }
    @BeforeAll
    public static void init(){
        baseURI="https://api.zippopotam.us";
    }
    @Test
    public void test3(){
       // Response response=get("api.zippopotam.us/us/:state/:city");
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("state", "va")
                .and().pathParam("city","fairfax")
                .when().get("/us/{state}/{city}");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        JsonPath jsonPath=response.jsonPath();
        assertEquals("US",jsonPath.getString("'country abbreviation'"));
        assertEquals("United States",jsonPath.getString("'country'"));
        assertEquals("Fairfax",jsonPath.getString("'place name'"));
        List<String> placeNames=jsonPath.getList("places.'place name'");
        for(String each:placeNames){
            assert(each.contains("Fairfax"));
        }

        List<String>postCode=jsonPath.getList("places.'post code'");
        for(String each:postCode){
            assert (each.contains("22"));
        }

    }

}
