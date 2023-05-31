package whz.pti.db2projekt.model;

import java.util.ArrayList;

public class Adresse {
    public int getId() {
        return id;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public static ArrayList<Adresse> getAdresseList() {
        return adresseList;
    }

    private int id;
    private String strasse;
    private String stadt;
    private String postleitzahl;
    private String hausnummer;
    public Adresse(int id, String strasse, String stadt, String postleitzahl, String hausnummer) {
        this.id = id;
        this. strasse =  strasse;
        this.stadt = stadt;
        this.postleitzahl = postleitzahl;
        this.hausnummer = hausnummer;
    }

    private static ArrayList<Adresse> adresseList = new ArrayList<>();
    public static void addAdresse(Adresse adresse) {
        adresseList.add(adresse);
    }
    public static void printCount() {
        System.out.println("Anzahl Adresen: " + adresseList.stream().count());
    }
}
