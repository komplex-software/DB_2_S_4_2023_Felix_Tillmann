package whz.pti.db2projekt.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Kunde {
    int id;
    String vorname;
    String nachname;
    int adresse_id;
    int ansprechpartner_id;
    int anrede_id;

    private static ArrayList<Kunde> kundeList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public int getAdresse_id() {
        return adresse_id;
    }

    public int getAnsprechpartner_id() {
        return ansprechpartner_id;
    }

    public int getAnrede_id() {
        return anrede_id;
    }

    public static ArrayList<Kunde> getKundeList() {
        return kundeList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setAdresse_id(int adresse_id) {
        this.adresse_id = adresse_id;
    }

    public void setAnsprechpartner_id(int ansprechpartner_id) {
        this.ansprechpartner_id = ansprechpartner_id;
    }

    public void setAnrede_id(int anrede_id) {
        this.anrede_id = anrede_id;
    }

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

    public static void printCount() {
        System.out.println("Anzahl Kunden: " + kundeList.stream().count());
    }

    public static void clearList() {
        kundeList.clear();
    }

    public String getAdresseString() {
        if (adresse_id < 1) {
            return "-";
        }
        Adresse adresse = Adresse.getAdresseList().stream().filter(a -> a.getId() == this.adresse_id).collect(Collectors.toList()).get(0);
        String adrString = adresse.getStadt()+" "+adresse.getPostleitzahl()+" "+adresse.getStrasse()+" "+adresse.getHausnummer();
        return adrString;
    }

    public String getAnsprechpartnerNameString() {
        if (ansprechpartner_id < 1) {
            return "-";
        }
        Mitarbeiter ansprechpartner = Mitarbeiter.getMitarbeiterList().stream().filter(mitarb -> mitarb.getId() == this.adresse_id).collect(Collectors.toList()).get(0);
        return ansprechpartner.getVorname() + " " + ansprechpartner.getNachname();
    }

    public String getAnredeWortString() {
        if (anrede_id < 1) {
            return "-";
        }
        Anrede anrede = Anrede.getAnredeList().stream().filter(anred -> anred.getId() == this.adresse_id).collect(Collectors.toList()).get(0);
        return anrede.getAnredewort();
    }

    @Override
    public String toString() {
        try {
            Anrede anrede = Anrede.getAnredeList().stream().filter(a -> a.getId() == anrede_id).collect(Collectors.toList()).get(0);
            return anrede.getAnredewort() + " " + vorname + " " + nachname;
        } catch (Exception e) {
            return vorname + " " + nachname;
        }
    }
}
