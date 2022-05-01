package com.cybertek.OfficeHours;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class gsonExample {
    @Test
    public void gson_example(){

        Gson gson = new Gson();

        // JSON to JAVA collection --- De-Serialization

        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Melania\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 8916944276\n" +
                "}";

        Map<String, Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println("map = " + map);

        SpartanOscar spartan15 = gson.fromJson(myJsonData, SpartanOscar.class);
        System.out.println("spartan15 = " + spartan15);


// Serialization JAVA to JSON

        SpartanOscar spartanB22 = new SpartanOscar(200,"Elvin","Male",3456284);
        String jsonSpartanB22 = gson.toJson(spartanB22);
        System.out.println("jsonSpartanB22 = " + jsonSpartanB22);

    }
}
