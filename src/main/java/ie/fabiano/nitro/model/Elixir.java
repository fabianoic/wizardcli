package ie.fabiano.nitro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Elixir {

    private String name;
    private List<Ingredient> ingredients;

    @Override
    public String toString() {
        StringBuilder elixirMessage = new StringBuilder(name);
        elixirMessage.append(" - Ingredients: ");

        if (ingredients.isEmpty()) {
            elixirMessage.append("There aren't ingredients for this recipe.");
            return elixirMessage.toString();
        }

        for (int i = 0; i < ingredients.size(); i++) {
            elixirMessage.append(ingredients.get(i));

            if (i < ingredients.size() - 1) {
                elixirMessage.append(", ");
            }
        }
        return elixirMessage.toString();
    }
}
