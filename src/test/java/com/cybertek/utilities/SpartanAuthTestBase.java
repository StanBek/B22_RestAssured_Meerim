package com.cybertek.utilities;
import static io.restassured.RestAssured.baseURI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeAll;
public abstract class SpartanAuthTestBase {
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method
        baseURI = "http://44.195.19.167:7000";
    }
}
