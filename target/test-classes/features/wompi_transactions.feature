Feature: Creación de transacciones NEQUI

  # ==============================
  # Escenarios Exitosos
  # ==============================

  @exitoso_monto_valido
  Scenario: Pago NEQUI exitoso con monto válido
    Given el cliente quiere pagar con NEQUI
    When crea la transacción con monto "50.000 COP"
    Then la respuesta debe indicar que la transacción fue exitosa
    And se debe generar un ID de transacción válido

  @exitoso_monto_minimo
  Scenario: Pago NEQUI exitoso con monto mínimo
    Given el cliente quiere pagar con NEQUI
    When crea la transacción con monto "1 COP"
    Then la respuesta debe indicar que la transacción fue exitosa
    And se debe generar un ID de transacción válido

  @exitoso_moneda
  Scenario: Pago NEQUI exitoso con moneda correcta
    Given el cliente quiere pagar con NEQUI
    When crea la transacción con monto "100.000 COP"
    Then la respuesta debe indicar que la moneda es COP
    And la transacción se completa correctamente

  # ==============================
  # Escenarios Alternos / Fallidos
  # ==============================

  @fallido_monto_negativo
  Scenario: Pago NEQUI fallido por monto negativo
    Given el cliente quiere pagar con NEQUI
    When crea la transacción con monto "-500 COP"
    Then la respuesta debe indicar un error de monto inválido

  @fallido_telefono_incorrecto
  Scenario: Pago NEQUI fallido por número de teléfono incorrecto
    Given el cliente quiere pagar con NEQUI
    When crea la transacción con teléfono "123"
    Then la respuesta debe indicar un error de número de teléfono inválido

  @fallido_llave_invalida
  Scenario: Pago NEQUI fallido por llave pública/principal inválida
    Given el cliente quiere pagar con NEQUI
    When crea la transacción usando una llave inválida
    Then la respuesta debe indicar error de autenticación

  @fallido_campo_faltante
  Scenario: Pago NEQUI fallido por campo obligatorio faltante
    Given el cliente quiere pagar con NEQUI
    When crea la transacción sin enviar el correo del cliente
    Then la respuesta debe indicar error de campo obligatorio faltante