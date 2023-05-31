package whz.pti.db2projekt.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adresse {
    private int id;
    private String strasse;
    private String stadt;
    private String postleitzahl;
    private String hausnummer;
    private static ArrayList<Adresse> adresseList = new ArrayList<>();
    public Adresse(int id, String strasse, String stadt, String postleitzahl, String hausnummer) {
        this.id = id;
        this. strasse =  strasse;
        this.stadt = stadt;
        this.postleitzahl = postleitzahl;
        this.hausnummer = hausnummer;
    }

    public static void addAdresse(Adresse adresse) {
        adresseList.add(adresse);
    }
}
