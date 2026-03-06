package ie.fabiano.nitro.client;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public abstract class BaseWizardClient<T> {

    protected final ObjectMapper mapper = new ObjectMapper();

    protected final HttpClient client = HttpClient.newHttpClient();

    private final String BASE_URL = "https://wizard-world-api.herokuapp.com";

    protected List<T> executeGet(String path, Class<T> instanceType) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .GET()
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), mapper.getTypeFactory().constructCollectionType(List.class, instanceType));
    }

    public abstract List<T> getAll() throws IOException, InterruptedException;
}
