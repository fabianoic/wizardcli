package ie.fabiano.nitro.commands;

import ie.fabiano.nitro.models.Elixir;
import ie.fabiano.nitro.models.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandImpl implements CommandI {

    private List<Ingredient> ingredientsApi;
    private List<Elixir> elixirsApi;
    private List<Ingredient> ingredients;

    public CommandImpl(List<Ingredient> ingredientsApi, List<Elixir> elixirsApi, List<Ingredient> ingredients) {
        this.ingredientsApi = ingredientsApi;
        this.elixirsApi = elixirsApi;
        this.ingredients = ingredients;
    }

    @Override
    public void addIngredient(List<Integer> ingredientIds) {
        ingredientIds.forEach(id -> {
            if (id >= ingredientsApi.size() || id < 0) {
                System.out.printf("The id %d is incorrect!\n", id);
            } else {
                ingredients.add(ingredientsApi.get(id));
            }
        });
        System.out.println("ingredient(s) successfully added to your inventory.");

    }

    @Override
    public void printIngredientsAvailable() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ingredientsApi.size(); i++) {
            result.append(String.format("[%d] - %s | ", i, ingredientsApi.get(i).getName()));

            if (i % 6 == 0 && i != 0) result.append("\n");
        }
        System.out.println(result);
    }

    @Override
    public void removeIngredient(int id) {
        Ingredient removedIngredient = ingredients.remove(id);
        if (removedIngredient != null) {
            System.out.println("Ingredient removed from the list.");
            return;
        }
        System.out.println("The ingredient is not in the list.");
    }

    @Override
    public void cleanIngredients() {
        this.ingredients = new ArrayList<>();
        System.out.println("Ingredients list cleaned.");
    }

    @Override
    public void printIngredients() {
        if (ingredients.isEmpty()) System.out.println("You don't have any ingredient added.");
        else {
            StringBuilder stringBuilder = new StringBuilder(String.format("You have %d ingredient(s) in your inventory:\n", ingredients.size()));
            for (int i = 0; i < ingredients.size(); i++) {
                stringBuilder.append(String.format("id:[%d] - %s, ", i, ingredients.get(i).getName()));
            }

            System.out.println(stringBuilder);
        }
    }

    @Override
    public void printElixirs() {
        if (ingredients.isEmpty()) {
            System.out.println("You don't have any ingredient available");
            return;
        }
        printIngredients();

        Set<String> availableIngredients = ingredients.stream().map(Ingredient::getName).collect(Collectors.toSet());

        List<Elixir> possibleElixirs = elixirsApi.stream()
                .filter(elixir -> {
                    if (elixir.getIngredients().isEmpty()) return false;
                    return elixir.getIngredients().stream().allMatch(
                            ingredient -> availableIngredients.contains(ingredient.getName()));
                })
                .collect(Collectors.toList());
        if (possibleElixirs.isEmpty()) System.out.println("There aren't elixirs available for this ingredients.");
        else possibleElixirs.forEach(System.out::println);
    }

    @Override
    public void printAllElixirs() {
        elixirsApi.forEach(System.out::println);
    }
}