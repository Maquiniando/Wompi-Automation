package co.com.wompi.tasks;

import co.com.wompi.models.TransaccionRequest;
import co.com.wompi.utils.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CrearTransaccion implements Task {

    private final TransaccionRequest transaccion;

    public static Response lastResponse;

    public CrearTransaccion(TransaccionRequest transaccion) {
        this.transaccion = transaccion;
    }

    public static CrearTransaccion con(TransaccionRequest transaccion) {
        return instrumented(CrearTransaccion.class, transaccion);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        lastResponse = RestAssured.given()
                .baseUri(ApiConfig.BASE_URL)          // <- usa la URL de ApiConfig
                .header("Authorization", "Bearer " + ApiConfig.PRIVATE_KEY) // <- usa la llave
                .contentType("application/json")
                .body(transaccion)
                .post("/transactions")                // <- endpoint específico
                .andReturn();
    }
}

