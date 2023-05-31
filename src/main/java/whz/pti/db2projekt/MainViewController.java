package whz.pti.db2projekt;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import whz.pti.db2projekt.model.*;

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
            loadAdressen(connection);
            loadAnreden(connection);
            loadFahrzeuge(connection);
            loadFahrzeugfarben(connection);
            loadFahrzeugmodelle(connection);
            loadFahrzeugtypen(connection);
            loadHatAnsprechpartner(connection);
            loadHatFarbe(connection);
            loadHersteller(connection);

            // Testen der Daten
            Mitarbeiter.printCount();
            Kunde.printCount();
            Adresse.printCount();
            Anrede.printCount();
            Fahrzeug.printCount();
            Fahrzeugfarbe.printCount();
            Fahrzeugmodell.printCount();
            Fahrzeugtyp.printCount();
            HatAnsprechpartner.printCount();
            HatFarben.printCount();
            Hersteller.printCount();

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

    private void loadAdressen(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Adresse;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Adresse newAdresse = new Adresse(
                    rs.getInt("id"),
                    rs.getString("straße"),
                    rs.getString("stadt"),
                    rs.getString("postleitzahl"),
                    rs.getString("hausnummer")
            );
            Adresse.addAdresse(newAdresse);
        }
    }

    private void loadAnreden(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Anrede;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Anrede newAnrede = new Anrede(
                    rs.getInt("id"),
                    rs.getString("anredewort")
            );
            Anrede.addAnrede(newAnrede);
        }
    }
    private void loadFahrzeuge(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Fahrzeug;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fahrzeug newFahrzeug = new Fahrzeug(
                    rs.getInt("id"),
                    rs.getInt("modell_id"),
                    rs.getFloat("kaufpreis"),
                    rs.getFloat("mietpreis"),
                    rs.getBoolean("istVermietet"),
                    rs.getInt("mietKunde_id"),
                    rs.getBoolean("istVerkauft"),
                    rs.getInt("kaufKunde_id"),
                    rs.getDate("letzterTÜV"),
                    rs.getInt("anzVorherigeBesitzer"),
                    rs.getInt("kilometerstand")
            );
            Fahrzeug.addFahrzeug(newFahrzeug);
        }
    }
    private void loadFahrzeugfarben(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Fahrzeugfarbe;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fahrzeugfarbe newFahrzeugfarbe = new Fahrzeugfarbe(
                    rs.getInt("id"),
                    rs.getString("farbname")
            );
            Fahrzeugfarbe.addFarbe(newFahrzeugfarbe);
        }
    }

    private void loadFahrzeugmodelle(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Fahrzeugmodell;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fahrzeugmodell newFahrzeugmodell = new Fahrzeugmodell(
                    rs.getInt("id"),
                    rs.getInt("hersteller_id"),
                    rs.getInt("fahrzeugtyp_id")
            );
            Fahrzeugmodell.addModell(newFahrzeugmodell);
        }
    }
    private void loadFahrzeugtypen(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Fahrzeugtyp;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fahrzeugtyp newFahrzeugtyp = new Fahrzeugtyp(
                    rs.getInt("id"),
                    rs.getString("bezeichnung")
            );
            Fahrzeugtyp.addFahrzeugtyp(newFahrzeugtyp);
        }
    }
    private void loadHatAnsprechpartner(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM HatAnsprechpartner;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            HatAnsprechpartner newHatAnsprechpartner = new HatAnsprechpartner(
                    rs.getInt("kunde_id"),
                    rs.getInt("mitarbeiter_id")
            );
            HatAnsprechpartner.addHatAnsprechpartner(newHatAnsprechpartner);
        }
    }

    private void loadHatFarbe(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM HatFarben;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            HatFarben newHatFarben = new HatFarben(
                    rs.getInt("fahrzeug_id"),
                    rs.getInt("farb_id")
            );
            HatFarben.addHatFarbe(newHatFarben);
        }
    }

    private void loadHersteller(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String sql = "SELECT * FROM Hersteller;";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Hersteller newHersteller = new Hersteller(
                    rs.getInt("id"),
                    rs.getString("name")
            );
            Hersteller.addHersteller(newHersteller);
        }
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }
}