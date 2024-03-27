package refuelTheCar.adapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpAdapter {

    private static HttpAdapter INSTANCE;

    public static HttpAdapter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HttpAdapter();
        }
        return INSTANCE;
    }

    public <T> T sendGetRequest(String url, TypeReference<T> valueType)
            throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        if (response.statusCode() != HttpURLConnection.HTTP_OK) {
            throw new HttpConnectTimeoutException(url);
        }
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.body(), valueType);
    }
}
