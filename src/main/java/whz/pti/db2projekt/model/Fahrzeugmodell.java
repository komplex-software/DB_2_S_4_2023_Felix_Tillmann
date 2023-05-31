package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Fahrzeugmodell {
    int id;
    int hersteller_id;
    int fahrzeugtyp_id;

    public Fahrzeugmodell(int id, int hersteller_id, int fahrzeugtyp_id) {
        this.id = id;
        this.hersteller_id = hersteller_id;
        this.fahrzeugtyp_id = fahrzeugtyp_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHersteller_id(int hersteller_id) {
        this.hersteller_id = hersteller_id;
    }

    public void setFahrzeugtyp_id(int fahrzeugtyp_id) {
        this.fahrzeugtyp_id = fahrzeugtyp_id;
    }

    public int getId() {
        return id;
    }

    public int getHersteller_id() {
        return hersteller_id;
    }

    public int getFahrzeugtyp_id() {
        return fahrzeugtyp_id;
    }

    public static ArrayList<Fahrzeugmodell> getModellList() {
        return modellList;
    }

    private static ArrayList<Fahrzeugmodell> modellList = new ArrayList<>();
    public static void addModell(Fahrzeugmodell modell) {
        modellList.add(modell);
    }
    public static void printCount() {
        System.out.println("Anzahl Modelle: " + modellList.stream().count());
    }
}
