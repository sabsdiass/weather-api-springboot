🌤️ Weather API – Proyecto Spring Boot
Práctica de DAW: integración de Geoapify (geocodificación) + OpenWeather (clima actual) con una interfaz HTML/Thymeleaf.
------------

🧩 Funcionalidad
La aplicación permite seleccionar una ciudad (São Paulo, Rio de Janeiro o Salvador).

El flujo es:
  > El usuario elige una ciudad en el frontend.
  > El backend obtiene sus coordenadas usando Geoapify.
  > Con esas coordenadas, el backend consulta OpenWeather.
  > El resultado se muestra en un widget visual.
____________________________________________________________________________________

🗂️ Estructura del proyecto:

src/main/java/com/daw/weather_api
│
├── geoapify
│   ├── application
│   │     └── WeatherInfoApp
│   ├── domain
│   │     ├── response/GeoApifyResponse
│   │     └── services/GeoApifyService
│   └── infrastructure
│         └── controllers/WeatherInfoController
│
├── openweather
│   ├── domain/response (DTOs generados desde OpenWeather)
│   └── domain/services/OpenWeatherService
│
├── shared
│     └── ApiService
│
└── WeatherApiApplication.java



src/main/resources
  ├─ templates/spweather.html
  ├─ static/
  │   └── js/js/app.js
  │   └── js/api/apiService.js
  │   └── css/style.css

____________________________________________________________________________________

⚙️ Configuración

spring.application.name=weather-api
server.port=8080

# Geoapify
geoapify.api.key=TU_API_KEY_AQUI

# OpenWeather (URL parametrizada por lat/lon)
openweather.api.current_weather=https://api.openweathermap.org/data/2.5/weather?lat=<lat>&lon=<lon>&units=metric&lang=es&appid=TU_API_KEY_AQUI

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

<img width="1512" height="864" alt="vista-final" src="https://github.com/user-attachments/assets/56977fe0-3247-4655-aa37-62e9710afb75" />

