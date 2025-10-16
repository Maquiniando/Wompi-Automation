package co.com.wompi.scripts;

import co.com.wompi.models.TransaccionRequest;
import co.com.wompi.utils.ApiConfig;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class WompiSignature {

    // Genera la firma HMAC para un request de transacción
    public static String generateSignature(TransaccionRequest request) {
        try {
            // Payload exacto que Wompi requiere: amount|currency|reference
            String payload = request.getAmount_in_cents() + "|"
                    + request.getCurrency() + "|"
                    + request.getReference();

            // Imprimir payload para debug
            System.out.println("Payload para firma: " + payload);

            // Crear HMAC con SHA256 usando la private key
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(ApiConfig.PRIVATE_KEY.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secretKey);

            // Generar hash
            byte[] hash = sha256_HMAC.doFinal(payload.getBytes("UTF-8"));

            // Convertir hash a hexadecimal
            StringBuilder result = new StringBuilder();
            for (byte b : hash) {
                result.append(String.format("%02x", b));
            }

            String signature = result.toString();

            // Imprimir la firma generada para debug
            System.out.println("Firma generada: " + signature);

            return signature;
        } catch (Exception e) {
            throw new RuntimeException("Error generando la firma HMAC", e);
        }
    }
}



