package com.cybertek.Day9;
import com.cybertek.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.cybertek.utilities.SpartanAuthTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JsonSchemaValidationTest  extends SpartanAuthTestBase {
    @DisplayName("Get request to  verify one spartan against to chema")
    @Test
    public void schemaValidation() {
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",10)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                .log().all();

    }
    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest(){

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cybertek/Day9/allSpartansSchema.json")));

    }

}