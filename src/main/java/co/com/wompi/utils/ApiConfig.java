package co.com.wompi.utils;

import io.restassured.RestAssured;

public class ApiConfig {
    public static final String BASE_URL = "https://api-sandbox.co.uat.wompi.dev/v1";
    public static final String PRIVATE_KEY = "prv_stagtest_5i0ZGIGiFcDQifYsXxvsny7Y37tKqFWg";

    static {
        // Ignorar certificados SSL (sandbox)
        RestAssured.useRelaxedHTTPSValidation();
    }
}