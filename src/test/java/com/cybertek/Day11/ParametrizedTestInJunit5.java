package com.cybertek.Day11;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;

public class ParametrizedTestInJunit5 {
 @ParameterizedTest
    @ValueSource(ints = {10,33,23,1,3,4,5,3,2,1,45})
    public void testMultipleNumbers(int number){
     System.out.println(number+" number");
     Assertions.assertTrue(number>5);
 }
 @ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","TJ"})
    public void testMultipleNames(String name){
     System.out.println("name "+name);
 }
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200
    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void testZipCodes(int num){
         given().baseUri("https://api.zippopotam.us")
                .and().pathParam("zipcode", num)
               .log().all().when().get("/us/{zipcode}")
                 .then().statusCode(200);
    }
}
