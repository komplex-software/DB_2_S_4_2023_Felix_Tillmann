package whz.pti.db2projekt.model;

public class Fahrzeugmodell {
    int id;
    int hersteller_id;
    int fahrzeugtyp_id;

    public Fahrzeugmodell(int id, int hersteller_id, int fahrzeugtyp_id) {
        this.id = id;
        this.hersteller_id = hersteller_id;
        this.fahrzeugtyp_id = fahrzeugtyp_id;
    }
}
