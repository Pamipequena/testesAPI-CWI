package br.com.restassuredapitesting.tests.ping.tests;
import br.com.restassuredapitesting.suites.Healthcheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.ping.requests.GetPingRequest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GetPingTests extends BaseTest {
    GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Category(Healthcheck.class)
    @DisplayName("Verifica se API está online")
    public void CheckPing() throws Exception{
        getPingRequest.ping()
                .then()
                .statusCode(201)
                .assertThat();
        System.out.println("API Online");
    }
}
