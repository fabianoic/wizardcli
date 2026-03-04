package ie.fabiano.nitro;

import ie.fabiano.nitro.commands.CommandI;
import ie.fabiano.nitro.commands.CommandImpl;
import ie.fabiano.nitro.models.Elixir;
import ie.fabiano.nitro.models.Ingredient;
import ie.fabiano.nitro.repositories.ElixirRepositoryImpl;
import ie.fabiano.nitro.repositories.IRepository;
import ie.fabiano.nitro.repositories.IngredientRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final IRepository<Elixir> elixirRepository = new ElixirRepositoryImpl();
    private static final IRepository<Ingredient> ingredientIRepository = new IngredientRepositoryImpl();
    private static CommandI command;

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Elixir> elixirApi = elixirRepository.getAll();
        List<Ingredient> ingredientsApi = ingredientIRepository.getAll();
        List<Ingredient> ingredients = new ArrayList<>();

        command = new CommandImpl(ingredientsApi, elixirApi, ingredients);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    return;
                case 1:
                    command.printIngredientsAvailable();
                    System.out.println("Write the id(s) (separated by ,):");
                    String ids = scanner.next();
                    List<Integer> ingredientIds = Arrays.stream(ids.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    command.addIngredient(ingredientIds);
                    break;
                case 2:
                    command.printIngredients();
                    break;
                case 3:
                    command.printIngredients();
                    if (ingredients.isEmpty()) System.out.println("You don't have ingredient to delet.");
                    else {
                        System.out.println("Write the ingredient id to be removed:");

                        int id = scanner.nextInt();
                        command.removeIngredient(id);
                    }
                    break;
                case 4:
                    command.printElixirs();
                    break;
                case 5:
                    command.printAllElixirs();
                    break;
                case 6:
                    command.cleanIngredients();
                    break;
                default:
                    System.out.println("Option not found. Try again.");
            }
        }
    }

    private static void printMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Digit the option you want and press enter\n");
        menu.append("1 - Buy Ingredients\n");
        menu.append("2 - Show Inventory\n");
        menu.append("3 - Sell Ingredient\n");
        menu.append("4 - Craftable Elixir(s)\n");
        menu.append("5 - Show Recipes\n");
        menu.append("6 - Clean Inventory\n");
        menu.append("0 - Exit");
        System.out.println(menu);
    }
}