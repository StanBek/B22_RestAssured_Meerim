package com.cybertek.day6;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.Region;
import com.cybertek.pojo.Regions;
import com.cybertek.utilities.HRTestBase;
import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ORDSPojoGetRequestTest extends HRTestBase {
    @Test
    public void regionTest(){
        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();
        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println(region1);
        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
    }
    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet(){

        Employee employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println(employee1);

    }
    @DisplayName("Get request to/regions and only get some values as a POJO class")
    @Test
    public void regionPojoTest(){
        Regions regions = get("/regions").then().statusCode(200)
                .extract().response().as(Regions.class);
        System.out.println(regions);
        //verify count is 4
        assertThat(regions.getCount(),is(4));
        //create empty list to store values
        List<String>regionNames=new ArrayList<>();
        List<Integer>regionIDs=new ArrayList<>();
        //get list of regions out of regions object
        List<Region>items=regions.getItems();
        //loop through each of the region, save their ids and names to empty list that we prepare
        for(Region region:items){
            regionIDs.add(region.getRId());
            regionNames.add(region.getRegion_name());

        }
        System.out.println("regionIDs = " + regionIDs);
        System.out.println("regionNames = " + regionNames);
        List<Integer>expectedRegionIDs= Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames=Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");



        //compare two result
        assertThat(regionIDs,is(expectedRegionIDs));
        assertThat(regionNames,is(expectedRegionNames));


    }
}
