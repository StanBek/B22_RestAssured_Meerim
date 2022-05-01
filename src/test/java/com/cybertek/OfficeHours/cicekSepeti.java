package com.cybertek.OfficeHours;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
public class cicekSepeti {
    String url = "https://www.getpostman.com/collections/5d9f72679607a60f23af";

    @Test
    public void test1(){
        Gson gson = new Gson();

        Response response = RestAssured.get(url);

        // Assert the status code

        assertEquals(200, response.statusCode());

        assertEquals("application/json; charset=utf-8",response.contentType());

        System.out.println("response.body().path(\"info.name\") = " + response.body().path("info.name"));
        assertEquals("Ciceksepeti API Test Project",response.body().path("info.name"));

        assertEquals("{{url}}/test/installment=1",response.body().path("item[0].response[0].originalRequest.url"));

        String jsonDataforInstallment1 = response.path("item[0].response[0].body");
        System.out.println("jsonDataforInstallment1 = " + jsonDataforInstallment1);


        // From JSON to JAVA de-serializition
        Map<String,Object> Installment2DataMap = gson.fromJson(jsonDataforInstallment1,Map.class);

        System.out.println("Installment1DataMap = " + Installment2DataMap);

        Map<String , Object> result = (Map<String, Object>) Installment2DataMap.get("result");
        System.out.println("result = " + result);

        Map<String , Object> data = (Map<String, Object>) result.get("data");

        System.out.println("data = " + data);

        List<Map<String,Object>> products = (List<Map<String, Object>>) data.get("products");

        System.out.println(products);

        for (int i = 0; i < products.size(); i++) {

            System.out.println(products.get(i).get("productGroupId"));
            assertEquals(1.0,products.get(i).get("productGroupId"));

        }





    }

}
