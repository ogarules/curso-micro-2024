Feature: Calculadora
  Como un usuario
  Quiero poder usar la Calculadora para realizar operaciones matematicas
  Para que no tenga que realizarlas a mano

  Scenario Outline: Ejecutar operacion <operation> con <num1> y <num2>
    Given Tenemos dos numeros '<num1>' y '<num2>'
    When el usuario realiza la operacion <operation>
    Then el resultado es '<result>'

  # Scenario: Ejecutar operacion suma con -2 y 3
  #   Given Tenemos dos numeros '-2' y '3'
  #   When el usuario realiza la operacion suma
  #   Then el resultado es '1'
  
  # Scenario: Ejecutar operacion resta con 10 y 15
  #   Given Tenemos dos numeros '10' y '15'
  #   When el usuario realiza la operacion resta
  #   Then el resultado es '-5'

  Examples: 
    | num1 | num2 | operation      | result |
    | -2   | 3    | suma           | 1      |
    | 10   | 15   | resta          | -5     |
    | 2    | 3    | multiplicacion | 6      |
    | -1   | -10  | suma           | -11    |