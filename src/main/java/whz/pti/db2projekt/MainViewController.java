package whz.pti.db2projekt;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import whz.pti.db2projekt.model.Kunde;
import whz.pti.db2projekt.model.Mitarbeiter;
import whz.pti.db2projekt.model.UserPermissions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainViewController {
    // ------------- Adresse --------------
    @FXML
    private TextField adresse_id;
    @FXML
    private TextField adresse_straße;
    @FXML
    private TextField adresse_stadt;
    @FXML
    private TextField adresse_postleitzahl;
    @FXML
    private TextField adresse_hausnummer;
    @FXML
    private Button adresse_speichern;
    @FXML
    private Button adresse_suchen;
    @FXML
    private Button adresse_anlegen;
    @FXML
    private TableView adresse_anzeige;
    // ------------- Adresse --------------
    // -------------- Anrede --------------
    @FXML
    private TextField anrede_id;
    @FXML
    private TextField anrede_anredewort;
    // -------------- Anrede --------------
    // ------------ Fahrzeuge -------------
    @FXML
    private TextField fahrzeug_id;
    @FXML
    private TextField fahrzeug_kaufpreis;
    @FXML
    private TextField fahrzeug_mietpreis;
    @FXML
    private TextField fahrzeug_kaufkunde;
    @FXML
    private TextField fahrzeug_letzterTuev;
    @FXML
    private TextField fahrzeug_anzVorherigeBesitzer;
    @FXML
    private TextField fahrzeug_kilometerstand;
    // ------------ Fahrzeuge -------------
    // ---------- Fahrzeugfarbe -----------
    @FXML
    private TextField fahrzeugfarbe_id;
    @FXML
    private TextField fahrzeugfarbe_farbname;
    // ---------- Fahrzeugfarbe -----------
    // ---------- Fahrzeugmodell ----------
    @FXML
    private TextField fahrzeugmodell_id;
    // ---------- Fahrzeugmodell ----------




    @FXML
    private TextField id;
    @FXML
    private TextField vorname;
    @FXML
    private TextField nachname;
    @FXML
    private TextField lohn;
    @FXML
    private TextField beschaeftigungsstart;
    @FXML
    private TextField verfuegbarkeit;

    @FXML
    private ComboBox anrede;
    @FXML
    private ComboBox adresse;

    @FXML
    private Button speichern;
    @FXML
    private Button anlegen;
    @FXML
    private Button suchen;

    @FXML
    private TableView anzeige;

    private Connection connection;
    private UserPermissions permissions = UserPermissions.READ; // read ist standard

    public void loadConnection() {

        try {

            // Laden der Daten
            loadMitarbeiter(connection);
            loadKunden(connection);

            // Testen der Daten
            Mitarbeiter.printMitarbeiterCount();
            Kunde.printMitarbeiterCount();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadMitarbeiter(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "select * from  Mitarbeiter;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Mitarbeiter newMitarbeiter = new Mitarbeiter(
                    rs.getString("Vorname"),
                    rs.getString("Nachname"),
                    rs.getInt("Adresse_ID"),
                    rs.getInt("Anrede_ID"),
                    rs.getFloat("Lohn"),
                    rs.getDate("BeschäftigungsStart"),
                    rs.getBoolean("Verfügbar")
            );
            Mitarbeiter.addMitarbeiter(newMitarbeiter);
        }
    }

    private void loadKunden(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Kunde;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Kunde newKunde = new Kunde(
                    rs.getInt("id"),
                    rs.getString("Vorname"),
                    rs.getString("Nachname"),
                    rs.getInt("adresse_id"),
                    rs.getInt("ansprechpartner_id"),
                    rs.getInt("anrede_id")
            );
            Kunde.addKunde(newKunde);
        }
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }
}