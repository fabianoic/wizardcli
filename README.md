# 🧙‍♂️ Wizard CLI Project

A lightweight command-line interface (CLI) application designed to interact with [The Wizard World API](https://github.com/MossPiglets/WizardWorldAPI). This tool allows you to manage a magical inventory and discovers which elixirs you can brew based on the ingredients you currently own.

---

## 🚀 How to Run
You will need Java 11 or newer.

To start the application, navigate to your project folder in the terminal and locate the `.jar` file in the `out` directory.

Use the following command:

```bash
java -jar out/artifacts/WizardCLI_jar/WizardCLI.jar
```

---

## 📜 How to Use

When the program starts, a menu with 7 options will be displayed. To interact with the system, simply type the number of your choice and press **Enter**.

### Menu Options

* **1 - Buy Ingredients:** Displays all available ingredients. You can purchase items by typing the **Ingredient IDs** separated by commas (e.g., `1, 4, 12`).
* **2 - Show Inventory:** Lists all items currently stored in your inventory.
* **3 - Sell Ingredient:** Displays your inventory and allows you to remove a specific item by typing its **corresponding ID**.
* **4 - Craftable Elixir(s):** Compares your current inventory against the database and lists all Elixirs that you have the ingredients to craft.
* **5 - Show Recipes:** Displays the complete book of existing magical recipes.
* **6 - Clean Inventory:** Removes all items from your inventory at once.
* **0 - Exit:** Safely closes the application.
