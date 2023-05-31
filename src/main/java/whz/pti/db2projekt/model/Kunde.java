package whz.pti.db2projekt.model;

public class Kunde {
    int id;
    String vorname;
    String nachname;
    int adresse_id;
    int ansprechpartner_id;
    int anrede_id;

    public Kunde(int id, String vorname, String nachname, int adresse_id, int ansprechpartner_id, int anrede_id) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse_id = adresse_id;
        this.ansprechpartner_id = ansprechpartner_id;
        this.anrede_id = anrede_id;
    }

}
