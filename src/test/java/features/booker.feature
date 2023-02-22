Feature: Probar API Testing RestFul-Booker contiene end-points que permiten Crear, Leer, Actualizar y Eliminar reservas

  @Create
    @api
  Scenario Outline: Crear Booking
    Given Ejecutar el servicio CreateBooking y validar el codigo estado "<statusCode>"
    And Ejecutar el servicio UpdateBooking y validar el codigo estado "<statusCode>"
    And Ejecutar el servicio PartialUpdateBooking y validar el codigo estado "<statusCode>"
    Then Ejecutar el servicio DeleteBooking y validar el codigo estado "<statusCodeDeleted>" "<responseBody>"

    Examples:
      | statusCode | statusCodeDeleted | responseBody |
      | 200        | 201               | Created      |

  @Update
    @api
  Scenario Outline: Actualizar Booking
    Given Ejecutar el servicio CreateBooking y validar el codigo estado "<statusCode>"
    And Ejecutar el servicio UpdateBooking y validar el codigo estado "<statusCode>"
    Then Ejecutar el servicio DeleteBooking y validar el codigo estado "<statusCodeDeleted>" "<responseBody>"
    Examples:
      | statusCode | statusCodeDeleted | responseBody |
      | 200        | 201               | Created      |

  @PartialUpdateBooking
    @api
  Scenario Outline: Actualización parcial Booking
    Given Ejecutar el servicio CreateBooking y validar el codigo estado "<statusCode>"
    Given Ejecutar el servicio PartialUpdateBooking y validar el codigo estado "<statusCode>"
    Then Ejecutar el servicio DeleteBooking y validar el codigo estado "<statusCodeDeleted>" "<responseBody>"
    Examples:
      | statusCode | statusCodeDeleted | responseBody |
      | 200        | 201               | Created      |

  @Delete
    @api
  Scenario Outline: Eliminar Booking
    Given Ejecutar el servicio CreateBooking y validar el codigo estado "<statusCode>"
    Then Ejecutar el servicio DeleteBooking y validar el codigo estado "<statusCodeDeleted>" "<responseBody>"
    Examples:
      | statusCode | statusCodeDeleted | responseBody |
      | 200        | 201               | Created      |

  @Delete
    @api
  Scenario Outline: Eliminar Booking sin ID
    Then Ejecutar el servicio DeleteBooking sin id y validar el codigo estado "<statusCodeDeleted>" "<responseBody>"
    Examples:
      | statusCode | statusCodeDeleted | responseBody       |
      | 200        | 405               | Method Not Allowed |

  @HealthCheck
    @api
  Scenario Outline: HealthCheck Booking
    Given Ejecutar el servicio HealthCheck y validar el codigo estado "<statusCode>" "<responseBody>"
    Examples:
      | statusCode | responseBody |
      | 201        | Created      |

  @Auth
  Scenario Outline: Auth Booking
    Given Ejecutar el servicio Auth y validar el codigo estado "<statusCode>"
    Examples:
      | statusCode |
      | 200        |
