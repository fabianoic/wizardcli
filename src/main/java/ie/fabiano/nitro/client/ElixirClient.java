package ie.fabiano.nitro.client;

import ie.fabiano.nitro.model.Elixir;

import java.io.IOException;
import java.util.List;

public class ElixirClient extends BaseWizardClient<Elixir> {

    @Override
    public List<Elixir> getAll() throws IOException, InterruptedException {
        return executeGet("/Elixirs", Elixir.class);
    }
}
