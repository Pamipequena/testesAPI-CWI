package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    public Response newBooking(JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .body(payload.toString())
                .post("booking/");
    }

    public Response newBookingInvalid(JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "invalido")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .body(payload.toString())
                .post("booking/");
    }

}
