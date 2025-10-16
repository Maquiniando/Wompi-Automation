package co.com.wompi.scripts;

import co.com.wompi.models.TransaccionRequest;
import co.com.wompi.tasks.CrearTransaccion;
import co.com.wompi.questions.TransaccionExitosa;
import net.serenitybdd.screenplay.Actor;

public class TestIntegracionApi {

    public static void main(String[] args) {

        // Crear actor
        Actor cliente = Actor.named("Cliente");

        // ================= Crear transacción =================
        int monto = 5000000; // 50.000 COP

        // Token dinámico que ya obtuviste
        String acceptanceToken = "eyJhbGciOiJIUzI1NiJ9.eyJjb250cmFjdF9pZCI6NTA3LCJwZXJtYWxpbmsiOiJodHRwczovL3dvbXBpLmNvbS9hc3NldHMvZG93bmxvYWRibGUvcmVnbGFtZW50by1Vc3Vhcmlvcy1Db2xvbWJpYS5wZGYiLCJmaWxlX2hhc2giOiJkYzJkNGUzMDVlNGQzNmFhYjhjYzU3N2I1YTY5Nzg1MSIsImppdCI6IjE3NjA1OTYyNTctNTI1NzgiLCJlbWFpbCI6IiIsImV4cCI6MTc2MDU5OTg1N30.YPsyBl6Pdaf-tdKAnS5hBrVSF67gGu1b8zKolwOlWmo";

        // Crear request de transacción
        TransaccionRequest request = TransaccionRequest.builder()
                .amount_in_cents(monto)
                .currency("COP")
                .customer_email("cliente@ejemplo.com")
                .acceptance_token(acceptanceToken)
                .payment_method(
                        TransaccionRequest.PaymentMethod.builder()
                                .type("NEQUI")
                                .phone_number("3001234567")
                                .build()
                )
                .build();

        System.out.println("Referencia actual: " + request.getReference());

        // ================= Generar firma =================
        String signature = WompiSignature.generateSignature(request);
        request.setSignature(signature); // ✅ Asignamos la firma al request

        System.out.println("Firma generada: " + signature);

        // ================= Ejecutar tarea =================
        cliente.attemptsTo(CrearTransaccion.con(request));

        // ================= Validaciones =================
        boolean exito = TransaccionExitosa.isTrue().answeredBy(cliente);
        String transactionId = exito ? CrearTransaccion.lastResponse.jsonPath().getString("data.id") : null;

        // ================= Reporte =================
        System.out.println("=== Resultado de la transacción ===");
        System.out.println("¿Transacción exitosa? " + exito);
        System.out.println("ID de transacción: " + transactionId);
        System.out.println("=== Status === " + CrearTransaccion.lastResponse.getStatusCode());
        System.out.println("=== Response === " + CrearTransaccion.lastResponse.getBody().asPrettyString());
    }
}

