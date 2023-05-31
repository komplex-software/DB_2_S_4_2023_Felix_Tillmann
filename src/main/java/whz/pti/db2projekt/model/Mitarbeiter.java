package whz.pti.db2projekt.model;

import java.sql.Date;
import java.util.ArrayList;

public class Mitarbeiter {
    private String vorname;
    private String nachname;
    private int adresse_id;
    private int anrede_id;
    private float lohn;
    private Date beschaeftigung;
    private boolean verfuegbar;

    private static ArrayList<Mitarbeiter> mitarbeiterList = new ArrayList<>();

    public Mitarbeiter(String vorname, String nachname, int adresse_id, int anrede_id, float lohn, Date beschaeftigung, boolean verfuegbar) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse_id = adresse_id;
        this.anrede_id = anrede_id;
        this.lohn = lohn;
        this.beschaeftigung = beschaeftigung;
        this.verfuegbar = verfuegbar;
    }

    public static void addMitarbeiter(Mitarbeiter mitarbeiter) {
        mitarbeiterList.add(mitarbeiter);
    }

    public static void printMitarbeiterCount() {
        System.out.println("Anzahl Mitarbeiter: " + mitarbeiterList.stream().count());
    }

}
