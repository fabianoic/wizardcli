package ie.fabiano.nitro;

import ie.fabiano.nitro.client.BaseWizardClient;
import ie.fabiano.nitro.client.ElixirClient;
import ie.fabiano.nitro.client.IngredientClient;
import ie.fabiano.nitro.command.CommandI;
import ie.fabiano.nitro.command.CommandImpl;
import ie.fabiano.nitro.model.Elixir;
import ie.fabiano.nitro.model.Ingredient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final BaseWizardClient<Elixir> elixirClient = new ElixirClient();
    private static final BaseWizardClient<Ingredient> ingredientClient = new IngredientClient();

    Scanner scanner = new Scanner(System.in);

    void run() {
        List<Elixir> allAvailableElixirs;
        List<Ingredient> allAvailableIngredients;
        try {
            allAvailableElixirs = elixirClient.getAll();
            allAvailableIngredients = ingredientClient.getAll();
        } catch (IOException | InterruptedException e) {
            System.out.println("Unfortunately looks like some muggle turn off the system.");
            System.out.println(e.getMessage());
            return;
        }
        List<Ingredient> inventory = new ArrayList<>();

        CommandI command = new CommandImpl(allAvailableIngredients, allAvailableElixirs, inventory);

        while (true) {
            Integer option;
            printMenu();

            option = validInput();

            switch (option) {
                case 0:
                    System.out.println("System is terminating.");
                    return;
                case 1:
                    command.printAllIngredientsAvailable();
                    System.out.println("Write the id(s) (separated by ,):");
                    String ids = scanner.nextLine();
                    try {
                        List<Integer> ingredientIds = Arrays.stream(ids.split(","))
                                .map(Integer::parseInt)
                                .toList();
                        command.addItemsToInventory(ingredientIds);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please Enter a number.");
                        continue;
                    }
                    break;
                case 2:
                    command.printInventory();
                    break;
                case 3:
                    command.printInventory();
                    if (inventory.isEmpty()) return;

                    System.out.println("Write the ingredient id to be removed:");
                    Integer id = validInput();
                    if (id == null) continue;

                    command.removeItemFromInventory(id);
                    break;
                case 4:
                    command.printCraftableElixirs();
                    break;
                case 5:
                    command.printAllElixirs();
                    break;
                case 6:
                    command.cleanInventory();
                    break;
                case null:
                default:
                    System.out.println("Option not found. Try again.");
            }
        }
    }

    private static void printMenu() {
        String menu = """
                Digit the option you want and press enter
                1 - Buy Ingredients
                2 - Show Inventory
                3 - Sell Ingredient
                4 - Craftable Elixir(s)
                5 - Show Recipes
                6 - Clean Inventory
                0 - Exit""";
        System.out.println(menu);
    }

    private Integer validInput() {
        try {
            String stringInput = scanner.nextLine();
            return Integer.parseInt(stringInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please Enter a number.");
            return null;
        }
    }
}
