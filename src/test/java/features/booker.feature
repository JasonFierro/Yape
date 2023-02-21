Feature: Probar API Testing RestFul-Booker contiene end-points que permiten Crear, Leer, Actualizar y Eliminar reservas

  @Create
  Scenario Outline: Crear Booking
    Given Ejecutar el servicio CreateBooking y validar el codigo estado "<statusCode>"
    Examples:
      | statusCode | firsName | lastName | price | nombre |
      | 200        | Jason    | Fierro   | 2905  | Joel   |
