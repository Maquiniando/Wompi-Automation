package co.com.wompi.stepdefinitions;

import co.com.wompi.models.TransaccionRequest;
import co.com.wompi.tasks.CrearTransaccion;
import co.com.wompi.questions.TransaccionExitosa;
import co.com.wompi.utils.ObtenerToken;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class WompiTransaccionStepDefinitions {

    Actor actor = OnStage.theActorCalled("Cliente");

    // ===================== GIVEN =====================
    @Given("el cliente quiere pagar con NEQUI")
    public void elClienteQuierePagarConNEQUI() {
        // Actor ya definido, no necesita acción extra
    }

    // ===================== WHEN =====================
    @When("crea la transacción con monto {string}")
    public void creaLaTransaccionConMonto(String montoStr) {
        // Convertir "50.000 COP" a int
        int monto = Integer.parseInt(montoStr.replaceAll("[^0-9]", ""));

        // Obtener token dinámicamente
        String token = ObtenerToken.generarToken();

        // Crear request completo
        TransaccionRequest request = TransaccionRequest.builder()
                .amount_in_cents(monto)
                .currency("COP")
                .customer_email("cliente@ejemplo.com")
                .acceptance_token(token)
                .payment_method(
                        TransaccionRequest.PaymentMethod.builder()
                                .type("NEQUI")
                                .phone_number("3001234567")
                                .build()
                )
                .build();

        // Ejecutar tarea
        actor.attemptsTo(CrearTransaccion.con(request));
    }

    // ===================== THEN =====================
    @Then("la respuesta debe indicar que la transacción fue exitosa")
    public void laRespuestaDebeIndicarExito() {
        actor.should(seeThat(TransaccionExitosa.isTrue()));
    }

    @And("se debe generar un ID de transacción válido")
    public void seDebeGenerarIDTransaccionValido() {
        actor.should(seeThat(TransaccionExitosa.hasValidTransactionId()));
    }
}
