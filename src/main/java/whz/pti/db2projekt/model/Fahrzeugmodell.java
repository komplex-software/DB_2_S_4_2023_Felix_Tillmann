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
    private static ArrayList<Fahrzeugmodell> modellList = new ArrayList<>();
    public static void addModell(Fahrzeugmodell modell) {
        modellList.add(modell);
    }
    public static void printCount() {
        System.out.println("Anzahl Modelle: " + modellList.stream().count());
    }
}
