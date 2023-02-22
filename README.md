**Automatización YAPE**
**Descripción:** Se establece una automatización para API y aplicación movil el cual corresponde de los siguientes escenarios de prueba:

**Herramientas utilizadas**
* Intellij Idea
* JAVA
* MAVEN
* NODEJS -> Reportes Cucumber
* APPIUM
* APPIUM INSPECTOR
* ANDROID STUDIO

**Servicios API**
**Precondiciones:** Tener acceso a API correspondiente, los servicios API deben estar funcionando
Criterios de aceptación: Se debe consumir servicios POST, GET, PUT, PATCH, DELETE, creación de escenarios Happy Paths y UnHappy Paths,
automatizar las funcionalidades de: Auth, Booking y Ping, aserciones posibles.

**Escenarios:**
* Crear Booking
* Actualizar Booking
* Actualización parcial Booking
* Eliminar Booking
* HealthCheck Booking
* Auth Booking
* Eliminar Booking sin ID

Cada uno de estos es consumido por servicios restAsured con la librería de MAVEN,
se realizan aserciones dentro de los features como validar que venga el code response 200
o un texto dentro del body response.

**Automatización APK Booking**
Precondiciones: Tener acceso APK Booking, los servicios API apk Booking deben estar funcionando, debe tener APPIUM, ANDROID
Criterios de aceptación: Se debe realizar la creación de escenarios automatizados Happy Paths y UnHappy Paths, aserciones posibles.
Realizar la automatización de flujo completo con los datos solicitados.

**Escenarios:**
* Realizar una reserva de manera exitosa
* Ingresar una ciudad vacía y confirmar el error
* Validar los errores al momento de ingresar datos incorrectos de la tarjeta
* Validar la funcionalidad sort By
* Validar la funcionalidad filter
* Validar la funcionalidad Map
* Validar la funcionalidad favorite
* Validar la funcionalidad share
* Validar la funcionalidad en los tres puntos del menu al seleccionar el hotel

Estos escenarios se trabajaron con Gherkin, Cucumber con el fin de tener una mejor 
visibilidad en los escenarios de prueba con metodología BDD, se realizan flujos completos
al igual que flujos alternos.

Para la integración continua se establece utilizar Jenkins con el fin de poder ejecutar desde
un servidor y poderse conectar al repositorio GITHUB o GITLAB, por medio de la instrucción
mvn verify se ejecutará lo que se obtiene en el runner de nuestro proyecto el cual se explica
a continuación:
* features= donde estan alojados, por defecto se tiene que busque los features una carpeta anterior
* glue= donde estan alojados nuestros steps en lenguaje Gherkin
* plugin= Plugin utilizado para generar reportes Cucumber
* tags= Se puede utilizar para ejecutar diferentes pruebas sin necesidad de ejecutar todo el proyecto, ejemplo:
  tags = "@api and @Update", tags = "@api and not @Update", tags = "@Filtros and @api"

También se agregó un archivo de git yml con el fin de validar o hacer un testeo al momento de subir cambios
en nuestra rama mencionada, esto es utilizado por GitHub Actions Maven and Java.