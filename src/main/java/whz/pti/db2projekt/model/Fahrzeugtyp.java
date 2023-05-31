package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Fahrzeugtyp {
    int id;
    String bezeichnung;

    public Fahrzeugtyp(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public static void clearList() {
        typList.clear();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public static ArrayList<Fahrzeugtyp> getTypList() {
        return typList;
    }

    private static ArrayList<Fahrzeugtyp> typList = new ArrayList<>();
    public static void addFahrzeugtyp(Fahrzeugtyp modell) {
        typList.add(modell);
    }
    public static void printCount() {
        System.out.println("Anzahl Typen: " + typList.stream().count());
    }
}
