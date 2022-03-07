package fr.mleduc;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

@QuarkusTest
class ChangeCalculatorResourceTest {

    @InjectMock
    private ChangeService changeService;

    @Test
    void change() {
        final var changeRequest = new ChangeRequest();
        final var stockManuel = new Stock("manuel", 1, 1);
        changeRequest.stocks = List.of(stockManuel);
        changeRequest.total = 2;
        final var changeResult = new ChangeResult(1, Collections.singletonMap("manuel", 1));

        when(this.changeService.compute(2, stockManuel)).thenReturn(changeResult);

        given()
                .contentType("application/json")
                .body(changeRequest)
                .when()
                .put("/change")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("quantities.manuel", equalTo(1))
                .body("change", equalTo(1.0f));
    }
}