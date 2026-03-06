package ie.fabiano.nitro.client;

import ie.fabiano.nitro.model.Ingredient;

import java.io.IOException;
import java.util.List;

public class IngredientClient extends BaseWizardClient<Ingredient> {
    @Override
    public List<Ingredient> getAll() throws IOException, InterruptedException {
        return executeGet("/Ingredients", Ingredient.class);
    }
}
