package com.cybertek.utilities;
import com.cybertek.pojo.Spartan;
import com.github.javafaker.Faker;

import java.util.*;
public class SpartanUtil {
    public static  Map<String,Object>createSpartan(){
        Faker faker=new Faker();
        Spartan storeSpartan=new Spartan();
        storeSpartan.setName(faker.name().fullName());
        storeSpartan.setGender(faker.demographic().sex());
        storeSpartan.setPhone(Long.valueOf(faker.number().digits(10)));

        Map<String,Object>spartan=new HashMap<>();
        spartan.put("name",storeSpartan.getName());
        spartan.put("gender",storeSpartan.getGender());
        spartan.put("phone",storeSpartan.getPhone());
        return spartan;
    }
}
