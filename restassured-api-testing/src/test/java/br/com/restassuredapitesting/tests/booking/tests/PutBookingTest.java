package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando token")
    public void changeBookingToken() throws Exception{
      int idBooking = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

      putBookingRequest.changeAnBookingToken(idBooking, Utils.validPayloadBooking()).then()
              .statusCode(200)
              .time(lessThan(2L), TimeUnit.SECONDS)
              .body("size()", greaterThan(0));
      System.out.println("Reserva alterada com sucesso!");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva usando o Basic auth")
    public void changeBookingBasicAuth() throws Exception{
        int idBooking = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.basicAuth(idBooking, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(8L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
        System.out.println("Reserva utilizando o Basic Auth realizada com sucesso!");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void changeBookingNoToken() throws Exception{
        int idBooking = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.changeBookingNoToken(idBooking, Utils.validPayloadBooking()).then()
                .statusCode(403)
                .time(lessThan(8L), TimeUnit.SECONDS);
        System.out.println("ERRO 403, a reserva não pode ser alterada por falta de autorização");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token for invalido")
    public void changeBookingInvalidToken() throws Exception{
        int idBooking = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.changeBookingTokenInvalid(idBooking, Utils.validPayloadBooking()).then()
                .statusCode(403)
                .time(lessThan(8L), TimeUnit.SECONDS);

        System.out.println("ERRO 403, reserva não alterada, token invalido!");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void changeBookingNoExisting() throws Exception{

        putBookingRequest.changeAnBookingToken(589, Utils.validPayloadBooking()).then()
                .statusCode(405)
                .time(lessThan(8L), TimeUnit.SECONDS);
        System.out.println("ERRO 405, reserva não existe!");
    }

}
