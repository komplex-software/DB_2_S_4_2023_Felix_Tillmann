package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class HatFarben {
    int fahrzeug_id;
    int farb_id;
    public HatFarben(int fahrzeug_id, int farb_id) {
        this.fahrzeug_id = fahrzeug_id;
        this.farb_id = farb_id;
    }
    private static ArrayList<HatFarben> hatFarbenList = new ArrayList<>();
    public static void addHatFarbe(HatFarben hatFarben) {
        hatFarbenList.add(hatFarben);
    }
    public static void printCount() {
        System.out.println("Anzahl HatFarben: " + hatFarbenList.stream().count());
    }
}
