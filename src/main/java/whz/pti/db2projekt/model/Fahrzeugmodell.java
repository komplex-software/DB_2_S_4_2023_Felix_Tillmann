package whz.pti.db2projekt.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Fahrzeugmodell {
    int id;
    int hersteller_id;
    int fahrzeugtyp_id;

    public Fahrzeugmodell(int id, int hersteller_id, int fahrzeugtyp_id) {
        this.id = id;
        this.hersteller_id = hersteller_id;
        this.fahrzeugtyp_id = fahrzeugtyp_id;
    }

    public static void clearList() {
        modellList.clear();
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

    public String getHerstellerNameString() {
        if (hersteller_id < 1) {
            return "-";
        }
        Hersteller hersteller = Hersteller.getHerstellerList().stream().filter(h -> h.getId() == this.hersteller_id).collect(Collectors.toList()).get(0);
        return hersteller.getName();
    }

    public String getFahrzeugtypNameString() {
        if (fahrzeugtyp_id < 1) {
            return "-";
        }
        Fahrzeugtyp fahrzeugtyp = Fahrzeugtyp.getTypList().stream().filter(ftyp -> ftyp.getId() == this.fahrzeugtyp_id).collect(Collectors.toList()).get(0);
        return fahrzeugtyp.getBezeichnung();
    }

    @Override
    public String toString() {
        try {
            Hersteller hersteller = Hersteller.getHerstellerList().stream().filter(h -> h.getId() == hersteller_id).collect(Collectors.toList()).get(0);
            Fahrzeugtyp fahrzeugtyp = Fahrzeugtyp.getTypList().stream().filter(t -> t.getId() == fahrzeugtyp_id).collect(Collectors.toList()).get(0);
            return hersteller.getName() + " " + fahrzeugtyp.getBezeichnung();
        } catch (Exception e) {
            return "-";
        }
    }
}
