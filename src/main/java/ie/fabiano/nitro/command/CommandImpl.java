package ie.fabiano.nitro.command;

import ie.fabiano.nitro.model.Elixir;
import ie.fabiano.nitro.model.Ingredient;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CommandImpl implements CommandI {

    private List<Ingredient> allAvailableIngredients;
    private List<Elixir> allAvailableElixirs;
    private List<Ingredient> inventory;

    @Override
    public void addItemsToInventory(List<Integer> ingredientIds) {
        ingredientIds.forEach(id -> {
            if (id >= allAvailableIngredients.size() || id < 0) {
                System.out.printf("The index %d is incorrect!\n", id);
            } else {
                inventory.add(allAvailableIngredients.get(id));
            }
        });
        System.out.println("ingredient(s) successfully added to your inventory.");

    }

    @Override
    public void printAllIngredientsAvailable() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < allAvailableIngredients.size(); i++) {
            result.append(String.format("index:[%d] - %s | ", i, allAvailableIngredients.get(i).getName()));

            if (i % 6 == 0 && i != 0) result.append("\n");
        }
        System.out.println(result);
    }

    @Override
    public void removeItemFromInventory(int id) {
        try {
            inventory.remove(id);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The index provided is not in the list.");
            return;
        }
        System.out.println("Ingredient removed from the list.");
    }

    @Override
    public void cleanInventory() {
        this.inventory.clear();
        System.out.println("Inventory cleaned.");
    }

    @Override
    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("You don't have any ingredient added.");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(String.format("You have %d ingredient(s) in your inventory:\n", inventory.size()));
        for (int i = 0; i < inventory.size(); i++) {
            stringBuilder.append(String.format("index:[%d] - %s", i, inventory.get(i).getName()));
            if (i < inventory.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        System.out.println(stringBuilder);
    }

    @Override
    public void printCraftableElixirs() {
        printInventory();

        Set<String> availableIngredients = inventory.stream().map(Ingredient::getName).collect(Collectors.toSet());

        List<Elixir> possibleElixirs = allAvailableElixirs.stream()
                .filter(elixir -> elixir.getIngredients().stream().allMatch(
                        ingredient -> availableIngredients.contains(ingredient.getName())))
                .toList();
        if (possibleElixirs.isEmpty()) System.out.println("There aren't elixirs available for this ingredients.");
        else possibleElixirs.forEach(System.out::println);
    }

    @Override
    public void printAllElixirs() {
        allAvailableElixirs.forEach(System.out::println);
    }
}