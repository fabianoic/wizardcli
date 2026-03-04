package ie.fabiano.nitro.commands;

import java.util.List;

public interface CommandI {

    void addIngredient(List<Integer> ingredientIds);

    void printIngredientsAvailable();

    void removeIngredient(int id);

    void cleanIngredients();

    void printIngredients();

    void printElixirs();

    void printAllElixirs();

}
