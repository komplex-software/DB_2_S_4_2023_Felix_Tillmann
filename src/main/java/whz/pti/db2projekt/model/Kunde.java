package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Kunde {
    int id;
    String vorname;
    String nachname;
    int adresse_id;
    int ansprechpartner_id;
    int anrede_id;

    private static ArrayList<Kunde> kundeList;

    public Kunde(int id, String vorname, String nachname, int adresse_id, int ansprechpartner_id, int anrede_id) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse_id = adresse_id;
        this.ansprechpartner_id = ansprechpartner_id;
        this.anrede_id = anrede_id;
    }

    public static void addKunde(Kunde kunde) {
        kundeList.add(kunde);
    }

    public static void printMitarbeiterCount() {
        System.out.println("Anzahl Kunden: " + kundeList.stream().count());
    }

}
