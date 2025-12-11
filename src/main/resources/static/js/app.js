// js/app.js – versão simples, SEM getCities

document.addEventListener("DOMContentLoaded", () => {
  console.log("[app] DOMContentLoaded");

  const citiesSelect = document.getElementById("cities");
  const cityNameEl = document.getElementById("city-name");
  const tempEl = document.getElementById("temp");
  const descEl = document.getElementById("description");
  const iconEl = document.getElementById("icon");

  if (!citiesSelect || !cityNameEl || !tempEl || !descEl || !iconEl) {
    console.error("[app] Elemento esperado não encontrado.");
    return;
  }

  // Carrega a primeira cidade quando a página abre
  if (citiesSelect.value) {
    loadWeatherForSelectedCity();
  }

  citiesSelect.addEventListener("change", () => {
    console.log("[app] Cidade trocada para:", citiesSelect.value);
    loadWeatherForSelectedCity();
  });

  async function loadWeatherForSelectedCity() {
    const city = citiesSelect.value;

    if (!city) {
      cityNameEl.textContent = "Selecione uma cidade";
      tempEl.textContent = "-- ºC";
      descEl.textContent = "---";
      iconEl.removeAttribute("src");
      iconEl.alt = "";
      return;
    }

    console.log("[app] Buscando clima para:", city);

    cityNameEl.textContent = "Cargando...";
    tempEl.textContent = "-- ºC";
    descEl.textContent = "---";
    iconEl.removeAttribute("src");
    iconEl.alt = "";

    try {
      const data = await getWeatherByCity(city); // função de apiService.js
      console.log("[app] Clima recebido:", data);
      renderWeather(data);
    } catch (error) {
      console.error("[app] Erro ao obter clima:", error);
      cityNameEl.textContent = "Erro ao obter clima 😢";
      tempEl.textContent = "-- ºC";
      descEl.textContent = "---";
    }
  }

  function renderWeather(data) {
    if (!data) {
      cityNameEl.textContent = "Sem dados";
      tempEl.textContent = "-- ºC";
      descEl.textContent = "---";
      return;
    }

    const cityName = data.name || "Cidade";
    const country = data.sys && data.sys.country ? data.sys.country : "";
    const temp = data.main && data.main.temp != null ? data.main.temp : null;
    const description =
      data.weather && data.weather.length > 0
        ? data.weather[0].description
        : null;
    const iconCode =
      data.weather && data.weather.length > 0
        ? data.weather[0].icon
        : null;

    cityNameEl.textContent = `${cityName}${country ? " (" + country + ")" : ""}`;
    tempEl.textContent = temp != null ? `${temp.toFixed(1)} ºC` : "-- ºC";
    descEl.textContent = description || "---";

    if (iconCode) {
      iconEl.src = `https://openweathermap.org/img/wn/${iconCode}@2x.png`;
      iconEl.alt = description || "";
    } else {
      iconEl.removeAttribute("src");
      iconEl.alt = "";
    }
  }
});
