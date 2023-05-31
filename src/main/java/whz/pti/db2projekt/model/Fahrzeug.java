package whz.pti.db2projekt.model;

import java.sql.Date;

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

}
