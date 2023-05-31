package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Hersteller {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    int id;
    String name;
    public Hersteller(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<Hersteller> getHerstellerList() {
        return herstellerList;
    }

    private static ArrayList<Hersteller> herstellerList = new ArrayList<>();
    public static void addHersteller(Hersteller hersteller) {
        herstellerList.add(hersteller);
    }
    public static void printCount() {
        System.out.println("Anzahl Hersteller: " + herstellerList.stream().count());
    }
}
