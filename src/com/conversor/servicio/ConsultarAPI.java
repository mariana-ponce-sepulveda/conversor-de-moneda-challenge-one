package com.conversor.servicio;
import com.conversor.modelo.Conversor;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Clase encargada de realizar la conexión con la API de tasas de cambio (ExchangeRate).
 * Se comunica con el servidor, recibe el JSON y lo transforma en un objeto Conversor.
 */

public class ConsultarAPI {
    // Clave de autenticación de la API
    private static final String API_KEY = "fe1bacac4b677249283cc52f";

    // URL base del servicio ExchangeRate API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    /**
     * Método principal que realiza la consulta a la API.
     * @param monedaBase    Código ISO de la moneda base (ejemplo: "USD")
     * @param monedaDestino Código ISO de la moneda destino (ejemplo: "CLP")
     * @return Objeto Conversor con la información de la tasa actual.
     */
    public Conversor obtenerTasa(String monedaBase, String monedaDestino) {

        // Se construye la URL del endpoint final
        String endpoint = BASE_URL + API_KEY + "/pair/" + monedaBase + "/" + monedaDestino;

        // Cliente HTTP responsable de enviar la solicitud
        HttpClient cliente = HttpClient.newHttpClient();

        // Construcción del request a la API
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .build();

        // Hilo para mostrar animación mientras se consulta la API
        Thread animacion = new Thread(() -> {
            String[] frames = {"⏳", "⌛", "💱", "🔄"};
            int i = 0;
            try {
                while (!Thread.interrupted()) {
                    System.out.print("\rConsultando " + frames[i++ % frames.length]);
                    Thread.sleep(200);
                }
            } catch (InterruptedException ignored) {}
        });
        animacion.start();

        try {
            // Envía la solicitud HTTP y recibe la respuesta como texto
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            animacion.interrupt();  // Detiene la animación al recibir respuesta
            System.out.print("\r✅ Consulta completada.                     \n");

            // Verifica que la respuesta sea correcta (código 200)
            if (respuesta.statusCode() != 200) {
                System.out.println("⚠️ Error HTTP: " + respuesta.statusCode());
                return null;
            }

            // Convierte el JSON a un objeto Conversor usando Gson
            Conversor conversor = new Gson().fromJson(respuesta.body(), Conversor.class);

            // Valida que el objeto tenga datos válidos
            if (conversor == null || conversor.getConversion_rate() == 0) {
                System.out.println("⚠️ Tasa inválida o error en los datos.");
                return null;
            }

            return conversor;  // Retorna el objeto con la información de conversión

        } catch (IOException | InterruptedException e) {
            // Maneja errores de conexión o interrupciones
            animacion.interrupt();
            System.out.print("\r❌ Error al consultar la API.\n");
            System.out.println("Detalles: " + e.getMessage());
            return null;
        }
    }
}
