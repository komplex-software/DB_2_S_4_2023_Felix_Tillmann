package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Fahrzeugfarbe {
    int id;
    String farbname;

    public Fahrzeugfarbe(int id, String farbname) {
        this.id = id;
        this.farbname = farbname;
    }

    private static ArrayList<Fahrzeugfarbe> farbeList = new ArrayList<>();
    public static void addFarbe(Fahrzeugfarbe farbe) {
        farbeList.add(farbe);
    }
    public static void printCount() {
        System.out.println("Anzahl Farben: " + farbeList.stream().count());
    }
}
