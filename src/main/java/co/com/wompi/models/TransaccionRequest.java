package co.com.wompi.models;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class TransaccionRequest {
    private Integer amount_in_cents;
    private String currency;
    private String customer_email;
    @Builder.Default
    private String reference = "transaccion-" + UUID.randomUUID().toString(); // referencia única por default

    private String acceptance_token = "eyJhbGciOiJIUzI1NiJ9.eyJjb250cmFjdF9pZCI6NTA3LCJwZXJtYWxpbmsiOiJodHRwczovL3dvbXBpLmNvbS9hc3NldHMvZG93bmxvYWRibGUvcmVnbGFtZW50by1Vc3Vhcmlvcy1Db2xvbWJpYS5wZGYiLCJmaWxlX2hhc2giOiJkYzJkNGUzMDVlNGQzNmFhYjhjYzU3N2I1YTY5Nzg1MSIsImppdCI6IjE3NjA1OTUwNjQtMTI3MzQiLCJlbWFpbCI6IiIsImV4cCI6MTc2MDU5ODY2NH0.HbOim_VgcLngJOaiugBmfonA7vniRKS4YPLAYk4dnQs";
    private PaymentMethod payment_method;

    // ✅ Campo para la firma
    private String signature;

    @Data
    @Builder
    public static class PaymentMethod {
        private String type;
        private String phone_number;
    }
}
