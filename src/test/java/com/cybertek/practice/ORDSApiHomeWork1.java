package com.cybertek.practice;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class ORDSApiHomeWork1 extends HRTestBase {
    @Test
    public void test1(){
        /**
         * - Given accept type is Json
         * - Path param value- US
         * - When users sends request to /countries
         * - Then status code is 200
         * - And Content - Type is Json
         * - And country_id is US
         * - And Country_name is United States of America
         * - And Region_id is
         */
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"country_id\":\"US\"}")
                .when().get("/countries");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
         //String countryID=response.path("/country_name").toString();

       // System.out.println("country_id"+response.body().path("country_id").toString());

        JsonPath jsonPath=response.jsonPath();
        String countryID=response.path("items[0].country_id");
        String countryName=response.path("items[0].country_name");
        int regionID=response.path("items[0].region_id");
        assertEquals("US",countryID);
        assertEquals("United States of America",countryName);
        assertEquals(2,regionID);

    }

}
