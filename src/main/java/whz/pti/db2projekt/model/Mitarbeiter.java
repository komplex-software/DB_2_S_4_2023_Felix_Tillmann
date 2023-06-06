package whz.pti.db2projekt.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Mitarbeiter {
    int id;

    public static void clearList() {
        mitarbeiterList.clear();
    }

    public int getId() {
        return this.id;
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

    public void setAnrede_id(int anrede_id) {
        this.anrede_id = anrede_id;
    }

    public void setLohn(float lohn) {
        this.lohn = lohn;
    }

    public void setBeschaeftigungsstart(Date beschaeftigungsstart) {
        this.beschaeftigungsstart = beschaeftigungsstart;
    }

    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
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

    public int getAnrede_id() {
        return anrede_id;
    }

    public float getLohn() {
        return lohn;
    }

    public Date getBeschaeftigungsstart() {
        return beschaeftigungsstart;
    }

    public boolean isVerfuegbar() {
        return verfuegbar;
    }

    public static ArrayList<Mitarbeiter> getMitarbeiterList() {
        return mitarbeiterList;
    }

    private String vorname;
    private String nachname;
    private int adresse_id;
    private int anrede_id;
    private float lohn;
    private Date beschaeftigungsstart;
    private boolean verfuegbar;

    private static ArrayList<Mitarbeiter> mitarbeiterList = new ArrayList<>();

    public Mitarbeiter(int id, String vorname, String nachname, int adresse_id, int anrede_id, float lohn, Date beschaeftigungsstart, boolean verfuegbar) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse_id = adresse_id;
        this.anrede_id = anrede_id;
        this.lohn = lohn;
        this.beschaeftigungsstart = beschaeftigungsstart;
        this.verfuegbar = verfuegbar;
    }

    public static void addMitarbeiter(Mitarbeiter mitarbeiter) {
        mitarbeiterList.add(mitarbeiter);
    }

    public static void printCount() {
        System.out.println("Anzahl Mitarbeiter: " + mitarbeiterList.stream().count());
    }

    public String getAdresseString() {
        if (adresse_id < 1) {
            return "-";
        }
        Adresse adresse = Adresse.getAdresseList().stream().filter(a -> a.getId() == this.adresse_id).collect(Collectors.toList()).get(0);
        String adrString = adresse.getStadt()+" "+adresse.getPostleitzahl()+" "+adresse.getStrasse()+" "+adresse.getHausnummer();
        return adrString;
    }

    public String getAnredeString() {
        if (anrede_id < 1) {
            return "-";
        }
        Anrede anrede = Anrede.getAnredeList().stream().filter(anred -> anred.getId() == this.adresse_id).collect(Collectors.toList()).get(0);
        return anrede.getAnredewort();
    }
}
