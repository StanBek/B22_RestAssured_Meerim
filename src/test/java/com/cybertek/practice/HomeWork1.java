package com.cybertek.practice;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.cybertek.utilities.HRTestBase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class HomeWork1 extends HRTestBase {
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
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/countries/US");
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        JsonPath jsonPath=response.jsonPath();
        assertEquals("US",jsonPath.getString("'country_id'"));
        assertEquals("United States of America",jsonPath.getString("'country_name'"));
        assertEquals(2,jsonPath.getInt("'region_id'"));

    }

    /**
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        JsonPath jsonPath=response.jsonPath();
        List<String> allJobID=jsonPath.getList("items.job_id");
      //  System.out.println(allJobID.toString());
        for(String each:allJobID){
            assert(each.startsWith("SA"));
        }
        List<Integer>departmentID=jsonPath.getList("items.department_id");
        for(Integer each:departmentID){
            assertEquals(80,each);
        }
        int count=jsonPath.getInt("count");
        System.out.println(count);
        assertEquals(25,count);

    }
    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");
        assertEquals(200,response.statusCode());
        JsonPath jsonPath=response.jsonPath();
        List<Integer>regionID=jsonPath.getList("items.'region_id'");
        for(Integer each:regionID){
            assertEquals(3,each);
           assertEquals(6,jsonPath.getInt("count"));
           assertEquals("false",jsonPath.getString("hasMore"));
           List<String>country_Name= Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore");
           for(String eachCountry:country_Name){
               assertTrue(jsonPath.getString("items.country_name").contains(eachCountry));
           }

        }
    }
}
