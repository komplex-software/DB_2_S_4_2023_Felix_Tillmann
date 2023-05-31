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

    public void setFahrzeug_id(int fahrzeug_id) {
        this.fahrzeug_id = fahrzeug_id;
    }

    public void setFarb_id(int farb_id) {
        this.farb_id = farb_id;
    }

    public int getFahrzeug_id() {
        return fahrzeug_id;
    }

    public int getFarb_id() {
        return farb_id;
    }

    public static ArrayList<HatFarben> getHatFarbenList() {
        return hatFarbenList;
    }

    public static void printCount() {
        System.out.println("Anzahl HatFarben: " + hatFarbenList.stream().count());
    }
}
