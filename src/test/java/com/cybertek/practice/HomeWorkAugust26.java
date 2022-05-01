package com.cybertek.practice;
import com.cybertek.utilities.SpartanAuthTestBase;
import com.cybertek.utilities.SpartanUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.cybertek.utilities.SpartanAuthTestBase;

import java.util.LinkedHashMap;
import java.util.Map;

public class HomeWorkAugust26  extends SpartanAuthTestBase {
      /**
        As a homework,write a detailed test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */
      @DisplayName("GET /api/spartans as a admin expect 200" )
    @Test
    public void test1(){
          given().auth().basic("admin","admin")
                  .and().accept(ContentType.JSON)
         .when().get("/api/spartans")
                  .then().statusCode(200)
                  .log().all();
      }
      @DisplayName("POST /api/spartans as admin expect 201 ")
    @Test
    public void test2() {
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("admin","admin")
                 .and().accept(ContentType.JSON)
                 .contentType(ContentType.JSON).body(SpartanUtil.createSpartan())
                 .when().post("/api/spartans").then().assertThat().statusCode(201);

      }
      @DisplayName("PUT /api/spartans as admin expect 204 ")
    @Test
    public void test3(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("admin","admin")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON).pathParam("id",213)
                  .body(SpartanUtil.createSpartan())
                  .when().put("/api/spartans/{id}").then().statusCode(204).log().all();

      }
      @DisplayName("Patch /api/spartans as admin expect 204")
      @Test
      public void test9(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("admin","admin")
                  .and().contentType(ContentType.JSON)
                  .and().accept(ContentType.JSON).pathParam("id",107)
                  .body(SpartanUtil.createSpartan())
                  .when().patch("/api/spartans/{id}").then().statusCode(204).log().body();

      }
      @DisplayName("Delete /api/spartans as admin expect 204")
    @Test
    public void test4(){
          given().auth().basic("admin","admin")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON).pathParam("id",209)
                  .when().delete("/api/spartans/{id}").then().statusCode(204).log().all();
      }
      @DisplayName("Get /api/spartan as editor expect 200")
    @Test
    public void test5(){
          given().auth().basic("editor","editor")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON).when().get("/api/spartans")
                  .then().statusCode(200);
      }
      @DisplayName("Post /api/spartans as editor expect 201")
    @Test
    public void test6(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("editor","editor")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON)
                  .body(SpartanUtil.createSpartan())
                  .when().post("/api/spartans").then().assertThat().statusCode(201);
      }
      @DisplayName("PUT /api/spartans as editor expect 204 ")
    @Test
    public void test7(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("editor","editor")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON).pathParam("id",279)
                  .body(SpartanUtil.createSpartan())
                  .when().put("/api/spartans/{id}").then().statusCode(204);
      }
      @DisplayName("Patch /api/spartans as editor expect 204")
    @Test
    public void test8(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("editor","editor")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON)
                  .body(SpartanUtil.createSpartan()).log().body()
                  .pathParam("id",279)
                  .when().patch("/api/spartans/{id}").then().statusCode(204);
      }
      @DisplayName("DElete /api/spartans/ as editor expect ")
    @Test
    public void test10(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("editor","editor")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON)
                 .pathParam("id",279)
                  .when().delete("/api/spartans/{id}").then().statusCode(403);
      }
      @DisplayName("Get /api/spartans/ as user expect 200")
    @Test
    public void test11(){
          given().auth().basic("user","user")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON)
                  .when().get("/api/spartans").then().statusCode(200);
      }
      @DisplayName("Post /api/spartans as user expect  403")
    @Test
    public void test12(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("user","user")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON)

                  .when().body(SpartanUtil.createSpartan())
                  .when().post("/api/spartans").then().statusCode(403);
      }
      @DisplayName("PUT /api/spartans as user expect 403")
    @Test
    public void test13(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("user","user")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON)
                  .pathParam("id",279)
                  .when().body(SpartanUtil.createSpartan())
                  .when().put("/api/spartans/{id}").then().statusCode(403);
      }
      @DisplayName("Patch /api/spartans as a user expect 403")
    @Test
    public void test14(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("user","user")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON)
                  .pathParam("id",279)
                  .when().patch("/api/spartans/{id}").then().statusCode(403);
      }
      @DisplayName("Delete /api/spartans as a user expect 403")
    @Test
    public void test15(){
          SpartanUtil spartanUtil=new SpartanUtil();
          given().auth().basic("user","user")
                  .and().accept(ContentType.JSON)
                  .contentType(ContentType.JSON)
                  .pathParam("id",279)
                  .when().delete("/api/spartans/{id}").then().statusCode(403);

      }
}
