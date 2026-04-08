package main;

public class LootFactory {

    private static final int EXPECTED_NUM_FIELDS = 4;

    public static Loot create(String[] csvRecord) {

       
        if (csvRecord == null || csvRecord.length != EXPECTED_NUM_FIELDS) {
            return null;
        }

        String typeField = csvRecord[0].toUpperCase(); 

        String name = csvRecord[1];
        String rarity = csvRecord[2];

        switch (typeField) {

            case "WEAPON":
                int damage = Integer.parseInt(csvRecord[3]);
                return new Weapon(name, rarity, damage);

            case "CONSUMABLE":
                int restoreAmount = Integer.parseInt(csvRecord[3]);
                return new Consumable(name, rarity, restoreAmount);

            default:
                System.out.printf("Skipping unknown Loot type %s.%n", typeField);
                return null;
        }
    }
}