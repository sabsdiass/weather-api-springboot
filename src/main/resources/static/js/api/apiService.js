const API_BASE_URL = window.location.origin + "/api";

async function getWeatherByCity(city) {
  const url = `${API_BASE_URL}/weather?city=${encodeURIComponent(city)}`;
  console.log("[apiService] Chamando:", url);

  const response = await fetch(url);

  if (!response.ok) {
    console.error("[apiService] Erro HTTP:", response.status, response.statusText);
    throw new Error("Error al obtener el clima");
  }

  const data = await response.json();
  console.log("[apiService] Resposta OK:", data);
  return data;
}
