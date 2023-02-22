Feature: Realizar una reserva o alojamiento en la aplicación movil Booking con una habitación 2 personas adultas y un niño
  de cinco años se debe validar el valor hasta el final de la compra

  @Exitosa
  Scenario Outline: Realizar una reserva de manera exitosa
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    And Agregar fechas ida y vuelta
    When Seleccionar habitacion niño de cinco anios
    And Seleccionar hotel "<texto>"
    And Seleccionar habitaciones "<texto>"
    When Reservar paquete
    And Ingresar informacion "<texto>" "<direccion>" "<zipCode>" "<infoCiudad>" "<infoTelefono>"
    Then Ingresar tarjeta de credito "<tc>" fecha de expiracion "<te>" "<texto>"
    Examples:
      | texto    | ciudad | direccion | zipCode | infoCiudad |  | infoTelefono | tc               | te    |
      | US$2,468 | CUSCO  | cll 131c  | 111111  | Bogotá     |  | 3058198412   | 5254133674403564 | 11/25 |

  @ValidarErrores
  Scenario Outline: Ingresar una ciudad vacia y confirmar el error
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    Examples:
      | texto    | ciudad |
      | US$2,468 |        |

  @ValidarErrores
  Scenario Outline: Validar los errores al momento de ingresar datos incorrectos de la tarjeta
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    And Agregar fechas ida y vuelta
    When Seleccionar habitacion niño de cinco anios
    And Seleccionar hotel "<texto>"
    And Seleccionar habitaciones "<texto>"
    When Reservar paquete
    And Ingresar informacion "<texto>" "<direccion>" "<zipCode>" "<infoCiudad>" "<infoTelefono>"
    Then Ingresar tarjeta de credito "<tc>" fecha de expiracion y validar errores "<te>" "<texto>" "<mensajeErrorTarjeta>" "<mensajeErrorTCExpiracion>"
    Examples:
      | texto    | ciudad | direccion | zipCode | infoCiudad  |  | infoTelefono | tc                | te   | mensajeErrorTarjeta    | mensajeErrorTCExpiracion  |  |
      | US$2,468 | CUSCO  | cll 131c  | 111111  | Los Angeles |  | 3162534469   | 54444444444403564 | 11/4 | Card number is invalid | Please enter a valid year |  |
#      | US$2,468 | CUSCO  | cll 131c  | 111111  | Los Angeles |  | 3162534469   | 5444133674403     | 11    | Card length is incorrect | Credit card expiry date should be after check-in date. |  |

  @Filtro
  Scenario Outline: Validar la funcionalidad sort By
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    And Agregar fechas ida y vuelta
    When Seleccionar habitacion niño de cinco anios
    Then Validar la funcionalidad sort y volver
    Examples:
      | texto    | ciudad |
      | US$2,468 | CUSCO  |

  @Filtro
  Scenario Outline: Validar la funcionalidad filter
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    And Agregar fechas ida y vuelta
    When Seleccionar habitacion niño de cinco anios
    Then Validar la funcionalidad filter y volver
    Examples:
      | texto    | ciudad |
      | US$2,468 | CUSCO  |

  @Filtro
  Scenario Outline: Validar la funcionalidad Map
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    And Agregar fechas ida y vuelta
    When Seleccionar habitacion niño de cinco anios
    Then Validar la funcionalidad map y volver
    Examples:
      | texto    | ciudad |
      | US$2,468 | CUSCO  |

  @Filtro
  Scenario Outline: Validar la funcionalidad favorite
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    And Agregar fechas ida y vuelta
    When Seleccionar habitacion niño de cinco anios
    And Seleccionar hotel "<texto>"
    Then Hacer click en favorito
    Examples:
      | texto    | ciudad |
      | US$2,468 | CUSCO  |

  @Filtro
  Scenario Outline: Validar la funcionalidad share
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    And Agregar fechas ida y vuelta
    When Seleccionar habitacion niño de cinco anios
    And Seleccionar hotel "<texto>"
    Then Hacer click en share validar la informacion y volver
    Examples:
      | texto    | ciudad |
      | US$2,468 | CUSCO  |

  @Filtro
  Scenario Outline: Validar la funcionalidad en los tres puntos del menu al seleccionar el hotel
    Given Ingresar al sitio Booking
    And Buscar la ciudad "<ciudad>"
    And Agregar fechas ida y vuelta
    When Seleccionar habitacion niño de cinco anios
    And Seleccionar hotel "<texto>"
    Then Hacer click en mas opciones validar la informacion y volver
    Examples:
      | texto    | ciudad |
      | US$2,468 | CUSCO  |
