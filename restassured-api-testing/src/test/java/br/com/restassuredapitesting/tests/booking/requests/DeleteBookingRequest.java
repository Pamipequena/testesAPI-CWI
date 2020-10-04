package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Excluir uma reserva")
    public Response deleteReservationToken(int id, JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .body(payload.toString())
                .delete("booking/" + id);
    }

    @Step("Tentar excluir uma reserva sem token")
    public Response deleteReservationNoToken(int id, JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .delete("booking/" + id);
    }

}
