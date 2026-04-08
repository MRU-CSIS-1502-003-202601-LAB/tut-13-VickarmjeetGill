package main;

import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * Manages the inventory of RPG Loot.
 */
public class LootManager {
    private ArrayList<Loot> inventory;

    private LootManager() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Polymorphically displays all items in the inventory.
     */
    public void displayInventory() {
        System.out.println();
        System.out.println("--- Current Inventory ---");
        for (Loot item : inventory) {
            System.out.println(item.getName() + " [" + item.getRarity() + "] - " +
                    item.getEffectDescription());
        }
        System.out.println("-------------------------");
        System.out.println();
    }

    public static LootManager load(String filePath) throws FileNotFoundException {
        LootManager lootManager = new LootManager();

        Scanner fileScanner = new Scanner(new File(filePath));

        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine(); 
        }

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] csvRecord = line.split(",");
            Loot item = LootFactory.create(csvRecord);
            lootManager.add(item);
        }

        fileScanner.close();
        return lootManager;
    }

    public void add(Loot item) {
    if (item != null) {
        inventory.add(item);
    }
}

public void save(String filePath) throws FileNotFoundException {

    PrintWriter fileWriter = new PrintWriter(new File(filePath));

    fileWriter.println("TYPE,NAME,RARITY,SPECIAL_1");

    for (Loot item : inventory) {
        fileWriter.println(item.asCsvRow());
    }

    fileWriter.close();
}



}