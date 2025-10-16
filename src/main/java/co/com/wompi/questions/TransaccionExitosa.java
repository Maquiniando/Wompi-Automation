package co.com.wompi.questions;

import co.com.wompi.tasks.CrearTransaccion;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TransaccionExitosa implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return CrearTransaccion.lastResponse != null
                && CrearTransaccion.lastResponse.getStatusCode() == 201;
    }

    public static TransaccionExitosa isTrue() {
        return new TransaccionExitosa();
    }

    // Validar que se generó un ID
    public static Question<Boolean> hasValidTransactionId() {
        return actor -> CrearTransaccion.lastResponse != null
                && CrearTransaccion.lastResponse.jsonPath().getString("data.id") != null;
    }
}
