package com.cybertek.Day9;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class govXMLTest {

        /**send a get request to
         //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
         //get all the years
         //get all unknowns
         //get 2006 other
         get 2007 _address
         */
        @BeforeAll
        public  static void setUp(){
            baseURI="https://data.ct.gov";
        }
        @Test
        public void test1() {
            Response response = given().accept(ContentType.XML)
                    .when().get("/api/views/qm34-pq7e/rows.xml");
            XmlPath xmlPath=response.xmlPath();
            List<Integer>years=xmlPath.getList("response.row.row.year");
            System.out.println(years);
            List<Integer>unknown=xmlPath.getList("response.row.row.unknown");
            System.out.println(unknown);
           int other2005= xmlPath.getInt("response.row.row[2].other");
            System.out.println(other2005);
          String address2007=  xmlPath.getString("response.row.row[4].@_address");
            System.out.println(address2007);


        }
}



