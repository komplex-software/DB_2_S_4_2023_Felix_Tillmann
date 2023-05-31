package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Fahrzeugfarbe {
    int id;
    String farbname;

    public Fahrzeugfarbe(int id, String farbname) {
        this.id = id;
        this.farbname = farbname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFarbname(String farbname) {
        this.farbname = farbname;
    }

    public int getId() {
        return id;
    }

    public String getFarbname() {
        return farbname;
    }

    public static ArrayList<Fahrzeugfarbe> getFarbeList() {
        return farbeList;
    }

    private static ArrayList<Fahrzeugfarbe> farbeList = new ArrayList<>();
    public static void addFarbe(Fahrzeugfarbe farbe) {
        farbeList.add(farbe);
    }
    public static void printCount() {
        System.out.println("Anzahl Farben: " + farbeList.stream().count());
    }
}
