🌤️ Weather API – Proyecto Spring Boot
Práctica de DAW: consumo de la API REST de OpenWeather con Spring Boot y visualización del clima mediante Thymeleaf.
------------

🧩 Funcionalidad
La aplicación consume el endpoint de Current Weather de OpenWeather:
https://api.openweathermap.org/data/2.5/weather

El resultado se transforma en un DTO simplificado (SimpleWeather) y se muestra en un widget visual accesible desde:
http://localhost:8080/spweather

____________________________________________________________________________________

🗂️ Estructura del proyecto:

src/main/java/com/daw/weather_api
  ├─ application
  │    ├─ APIWeatherService
  │    ├─ SimpleWeather
  │    └─ SimpleWeatherService
  │
  ├─ domain/response
  │    ├─ WeatherResponse
  │    ├─ Main
  │    ├─ Weather
  │    └─ ...
  │
  ├─ infrastructure
  │    └─ SimpleWeatherViewController
  │
  └─ WeatherApiApplication.java

src/main/resources
  ├─ templates/spweather.html

____________________________________________________________________________________

⚙️ Configuración

En el archivo src/main/resources/application.properties:

spring.application.name=weather-api
server.port=8080

openweather.base-url=https://api.openweathermap.org/data/2.5/weather
openweather.lat=-23.5505
openweather.lon=-46.6333
openweather.units=metric
openweather.lang=es
openweather.api-key=TU_API_KEY_AQUI

____________________________________________________________________________________

▶️ Cómo ejecutar el proyecto

Clonar el repositorio:
git clone https://github.com/SEU_USUARIO/weather-api-springboot.git

Añadir tu API KEY(TU_API_KEY_AQUI) de OpenWeather en application.properties.

Ejecutar:
mvn spring-boot:run

Acceder:
http://localhost:8080/spweather

____________________________________________________________________________________

📸 Vista final del widget

<img width="1510" height="908" alt="view-appweather" src="https://github.com/user-attachments/assets/07e92dcb-1b2f-44e2-96ff-e229c9608a28" />

