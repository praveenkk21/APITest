
	package API;
	import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
	public class API {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			//import org.testng.annotations.Test;
			  RestAssured.baseURI="https://rahulshettyacademy.com";
			  String Actualaddress="30, side layout, cohen 09";
			  String placeid;
			  String response1=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "328600b48d3c139671a04137e561d0fa")
					  .body("{\r\n" + 
					  		"  \"location\": {\r\n" + 
					  		"    \"lat\": -38.383494,\r\n" + 
					  		"    \"lng\": 33.427362\r\n" + 
					  		"  },\r\n" + 
					  		"  \"accuracy\": 50,\r\n" + 
					  		"  \"name\": \"PRAVEEN\",\r\n" + 
					  		"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
					  		"  \"address\": \""+Actualaddress+"\",\r\n" + 
					  		"  \"types\": [\r\n" + 
					  		"    \"shoe park\",\r\n" + 
					  		"    \"shop\"\r\n" + 
					  		"  ],\r\n" + 
					  		"  \"website\": \"https://kt.com\",\r\n" + 
					  		"  \"language\": \"French-IN\"\r\n" + 
					  		"}\r\n" + 
					  		"")
					  //.header("accept","*/*").header("hha", 1).header("user", 1).header("instance_code", "working")
					  .when().post("/maps/api/place/add/json")
					  .then().assertThat().statusCode(200)
					  .extract().response().asString();
					  
					  JsonPath js=new JsonPath(response1);
					  System.out.println("This is response1"+response1);
					  placeid=js.getString("place_id");
					 System.out.println(placeid);
			  
			  
			  String response=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", ""+placeid+"").body("")
			  //.header("accept","*/*").header("hha", 1).header("user", 1).header("instance_code", "working")
			  .when().get("/maps/api/place/get/json")
			  .then().assertThat().statusCode(200)
			  .extract().response().asString();
			  
			  JsonPath js2=new JsonPath(response);
			  System.out.println("This is response2"+response);
			  String OutputAddres=js2.getString("address");
			  System.out.println(OutputAddres);
			  Assert.assertEquals(Actualaddress, OutputAddres);
			}
	}

