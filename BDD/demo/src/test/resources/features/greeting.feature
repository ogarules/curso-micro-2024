Feature: Testing greetings RESTFull API
  Los usuarios deben ser capaces de llamar a un GET y a un POST y probar la funcionalidad end to end

  Scenario: Saludar a un usuario por nombre
    Given Tenemos un usuario con id '1'
    When el usuario desea ser saludado
    Then el salud solicitado 'HElloo Test' es retornado