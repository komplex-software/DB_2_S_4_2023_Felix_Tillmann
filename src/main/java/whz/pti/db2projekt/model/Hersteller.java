package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Hersteller {
    int id;
    String name;
    public Hersteller(int id, String name) {
        this.id = id;
        this.name = name;
    }
    private static ArrayList<Hersteller> herstellerList = new ArrayList<>();
    public static void addHersteller(Hersteller hersteller) {
        herstellerList.add(hersteller);
    }
    public static void printCount() {
        System.out.println("Anzahl Hersteller: " + herstellerList.stream().count());
    }
}
