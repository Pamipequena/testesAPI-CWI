package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;


import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void createdNewBooking() throws Exception{
        postBookingRequest.newBooking(Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(8L), TimeUnit.SECONDS);
        System.out.println("Reserva Cadastrada com Sucesso!");

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void invalidBooking() throws Exception{
        postBookingRequest.newBooking(Utils.invalidPayloadBooking()).then()
                .statusCode(500)
                .time(lessThan(5L), TimeUnit.SECONDS);
        System.out.println("ERRO 500, Payload da reserva invalido! ");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar a criação de mais de uma reserva em sequencia")
    public void createNewBooking() throws Exception{

        for(int i = 1; i<=2; i++) {
            postBookingRequest.newBooking(Utils.validPayloadBooking()).then()
                    .statusCode(200)
                    .time(lessThan(8L), TimeUnit.SECONDS)
                    .body("size()", greaterThan(0));
            System.out.println("Reserva " + i + " cadastrada com sucesso!");

        }
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void invalidBookingPayload() throws Exception{
        postBookingRequest.newBooking(Utils.validExtraParamPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);
        System.out.println("Reserva criada com sucesso!");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void createdNewBookingInvalid() throws Exception{
        postBookingRequest.newBookingInvalid(Utils.validPayloadBooking()).then()
                .statusCode(418)
                .time(lessThan(8L), TimeUnit.SECONDS);
        System.out.println("ERRO 418, Header Accept invalido!");
    }

}
