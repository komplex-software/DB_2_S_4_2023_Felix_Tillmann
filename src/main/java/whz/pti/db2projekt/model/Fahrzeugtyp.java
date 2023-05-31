package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Fahrzeugtyp {
    int id;
    String bezeichnung;

    public Fahrzeugtyp(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    private static ArrayList<Fahrzeugtyp> typList = new ArrayList<>();
    public static void addFahrzeugtyp(Fahrzeugtyp modell) {
        typList.add(modell);
    }
    public static void printCount() {
        System.out.println("Anzahl Typen: " + typList.stream().count());
    }
}
