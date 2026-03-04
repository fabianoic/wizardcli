package ie.fabiano.nitro.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.fabiano.nitro.models.Ingredient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class IngredientRepositoryImpl implements IRepository<Ingredient> {

    private final String uri = "https://wizard-world-api.herokuapp.com/ingredients";

    private final ObjectMapper mapper = new ObjectMapper();

    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public List<Ingredient> getAll() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), new TypeReference<>() {
        });
    }
}
