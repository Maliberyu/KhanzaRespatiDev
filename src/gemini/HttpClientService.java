package gemini;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.URI;

public class HttpClientService {
    private final HttpClient httpClient;

    public HttpClientService() {
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(GeminiConfig.getTimeoutSeconds()))
            .build();
    }

    public String post(String url, String body) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .header("x-goog-api-key", GeminiConfig.getApiKey())
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .timeout(Duration.ofSeconds(GeminiConfig.getTimeoutSeconds()))
            .build();

        HttpResponse<String> response = httpClient.send(request, 
            HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("API request failed with status code: " 
                + response.statusCode() + " and body: " + response.body());
        }

        return response.body();
    }

}