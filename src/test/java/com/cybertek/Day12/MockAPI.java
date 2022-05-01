package com.cybertek.Day12;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class MockAPI {
    //https://ad4d587d-0eac-4777-b417-64ce0d9b7bcd.mock.pstmn.io
    @Test
    public void test1(){

        given().baseUri("https://ad4d587d-0eac-4777-b417-64ce0d9b7bcd.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"));

    }

    @Test
    public void test2(){

        given().baseUri("https://e787164d-adbd-474e-8c98-6796a1e3af70.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();

    }
    @Test
    public void test3(){

        given().baseUri("https://e787164d-adbd-474e-8c98-6796a1e3af70.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/meerim")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"));

    }

    @Test
    public void test4(){

        given().baseUri("https://e787164d-adbd-474e-8c98-6796a1e3af70.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/meerim")
                .prettyPrint();

    }

}
