package whz.pti.db2projekt.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fahrzeug {
    int id;
    int modell_id;
    float kaufpreis;
    float mietpreis;
    boolean istVermietet;
    int mietKunde_id;
    boolean istVerkauft;
    int kaufKunde_id;
    Date letzterTuev;
    int anzVorherigeBesitzer;
    int kilometerstand;

    public Fahrzeug(int id, int modell_id, float kaufpreis, float mietpreis, boolean istVermietet, int mietKunde_id, boolean istVerkauft, int kaufKunde_id, Date letzterTuev, int anzVorherigeBesitzer, int kilometerstand) {
        this.id = id;
        this.modell_id = modell_id;
        this.kaufpreis = kaufpreis;
        this.mietpreis = mietpreis;
        this.istVermietet = istVermietet;
        this.mietKunde_id = mietKunde_id;
        this.istVerkauft = istVerkauft;
        this.kaufKunde_id = kaufKunde_id;
        this.letzterTuev = letzterTuev;
        this.anzVorherigeBesitzer = anzVorherigeBesitzer;
        this.kilometerstand = kilometerstand;
    }

    private static ArrayList<Fahrzeug> fahrzeugList = new ArrayList<>();
    public static void addFahrzeug(Fahrzeug fahrzeug) {
        fahrzeugList.add(fahrzeug);
    }
    public static void printCount() {
        System.out.println("Anzahl Fahrzeuge: " + fahrzeugList.stream().count());
    }

    public static void clearList() {
        fahrzeugList.clear();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModell_id(int modell_id) {
        this.modell_id = modell_id;
    }

    public void setKaufpreis(float kaufpreis) {
        this.kaufpreis = kaufpreis;
    }

    public void setMietpreis(float mietpreis) {
        this.mietpreis = mietpreis;
    }

    public void setIstVermietet(boolean istVermietet) {
        this.istVermietet = istVermietet;
    }

    public void setMietKunde_id(int mietKunde_id) {
        this.mietKunde_id = mietKunde_id;
    }

    public void setIstVerkauft(boolean istVerkauft) {
        this.istVerkauft = istVerkauft;
    }

    public void setKaufKunde_id(int kaufKunde_id) {
        this.kaufKunde_id = kaufKunde_id;
    }

    public void setLetzterTuev(Date letzterTuev) {
        this.letzterTuev = letzterTuev;
    }

    public void setAnzVorherigeBesitzer(int anzVorherigeBesitzer) {
        this.anzVorherigeBesitzer = anzVorherigeBesitzer;
    }

    public void setKilometerstand(int kilometerstand) {
        this.kilometerstand = kilometerstand;
    }

    public int getId() {
        return id;
    }

    public int getModell_id() {
        return modell_id;
    }

    public float getKaufpreis() {
        return kaufpreis;
    }

    public float getMietpreis() {
        return mietpreis;
    }

    public boolean isIstVermietet() {
        return istVermietet;
    }

    public int getMietKunde_id() {
        return mietKunde_id;
    }

    public boolean isIstVerkauft() {
        return istVerkauft;
    }

    public int getKaufKunde_id() {
        return kaufKunde_id;
    }

    public Date getLetzterTuev() {
        return letzterTuev;
    }

    public int getAnzVorherigeBesitzer() {
        return anzVorherigeBesitzer;
    }

    public int getKilometerstand() {
        return kilometerstand;
    }

    public static ArrayList<Fahrzeug> getFahrzeugList() {
        return fahrzeugList;
    }

    public String getMietKundeNameString() {
        if (mietKunde_id < 1) {
            return "-";
        }
        Kunde mietKunde = Kunde.getKundeList().stream().filter(kunde -> kunde.getId() == this.mietKunde_id).collect(Collectors.toList()).get(0);
        return mietKunde.getNachname() + " " + mietKunde.getVorname();
    }

    public String getKaufKundeNameString() {
        if (kaufKunde_id < 1) {
            return "-";
        }
        Kunde kaufKunde = Kunde.getKundeList().stream().filter(kunde -> kunde.getId() == this.kaufKunde_id).collect(Collectors.toList()).get(0);
        return kaufKunde.getNachname() + " " + kaufKunde.getVorname();
    }
}
