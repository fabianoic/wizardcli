package ie.fabiano.nitro.command;

import java.util.List;

public interface CommandI {

    void addItemsToInventory(List<Integer> ingredientIds);

    void printAllIngredientsAvailable();

    void removeItemFromInventory(int id);

    void cleanInventory();

    void printInventory();

    void printCraftableElixirs();

    void printAllElixirs();

}
