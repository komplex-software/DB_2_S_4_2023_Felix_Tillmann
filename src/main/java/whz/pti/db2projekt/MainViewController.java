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

    @FXML
    private Button anrede_speichern;
    @FXML
    private Button anrede_suchen;
    @FXML
    private Button anrede_anlegen;
    @FXML
    private TableView anrede_anzeige;
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

    @FXML
    private ComboBox fahrzeug_modell;
    @FXML
    private ComboBox fahrzeug_istVermietet;

    @FXML
    private Button fahrzeug_speichern;
    @FXML
    private Button fahrzeug_suchen;
    @FXML
    private Button fahrzeug_anlegen;
    @FXML
    private TableView fahrzeug_anzeige;
    // ------------ Fahrzeuge -------------
    // ---------- Fahrzeugfarbe -----------
    @FXML
    private TextField fahrzeugfarbe_id;
    @FXML
    private TextField fahrzeugfarbe_farbname;

    @FXML
    private Button fahrzeugfarbe_speichern;
    @FXML
    private Button fahrzeugfarbe_suchen;
    @FXML
    private Button fahrzeugfarbe_anlegen;
    @FXML
    private TableView fahrzeugfarbe_anzeige;
    // ---------- Fahrzeugfarbe -----------
    // ---------- Fahrzeugmodell ----------
    @FXML
    private TextField fahrzeugmodell_id;

    @FXML
    private ComboBox fahrzeugmodell_hersteller;
    @FXML
    private ComboBox fahrzeugmodell_fahrzeugtyp;

    @FXML
    private Button fahrzeugmodell_speichern;
    @FXML
    private Button fahrzeugmodell_suchen;
    @FXML
    private Button fahrzeugmodell_anlegen;
    @FXML
    private TableView fahrzeugmodell_anzeige;
    // ---------- Fahrzeugmodell ----------
    // ----------- Fahrzeugtyp ------------
    @FXML
    private TextField fahrzeugtyp_id;
    @FXML
    private TextField fahrzeugtyp_bezeichnung;

    @FXML
    private Button fahrzeugtyp_speichern;
    @FXML
    private Button fahrzeugtyp_suchen;
    @FXML
    private Button fahrzeugtyp_anlegen;
    @FXML
    private TableView fahrzeugtyp_anzeige;
    // ----------- Fahrzeugtyp ------------
    // -------- HatAnsprechpartner --------
    @FXML
    private ComboBox hatAnsprechpartner_fahrzeug;
    @FXML
    private ComboBox hatAnsprechpartner_farbe;

    @FXML
    private Button hatAnsprechpartner_speichern;
    @FXML
    private Button hatAnsprechpartner_suchen;
    @FXML
    private Button hatAnsprechpartner_anlegen;
    @FXML
    private TableView hatAnsprechpartner_anzeige;
    // -------- HatAnsprechpartner --------
    // ------------ HatFarben -------------
    @FXML
    private ComboBox hatFarben_fahrzeug;
    @FXML
    private ComboBox hatFarben_farbe;

    @FXML
    private Button hatFarben_speichern;
    @FXML
    private Button hatFarben_suchen;
    @FXML
    private Button hatFarben_anlegen;
    @FXML
    private TableView hatFarben_anzeige;
    // ------------ HatFarben -------------
    // ------------ Hersteller -------------
    @FXML
    private TextField hersteller_id;
    @FXML
    private TextField hersteller_name;

    @FXML
    private Button hersteller_speichern;
    @FXML
    private Button hersteller_suchen;
    @FXML
    private Button hersteller_anlegen;
    @FXML
    private TableView hersteller_anzeige;
    // ------------ Hersteller -------------
    // --------------- Kunde ---------------
    @FXML
    private TextField kunde_id;
    @FXML
    private TextField kunde_vorname;
    @FXML
    private TextField kunde_nachname;

    @FXML
    private ComboBox kunde_anrede;
    @FXML
    private ComboBox kunde_adresse;
    @FXML
    private ComboBox kunde_ansprechpartner;

    @FXML
    private Button kunde_speichern;
    @FXML
    private Button kunde_suchen;
    @FXML
    private Button kunde_anlegen;
    @FXML
    private TableView kunde_anzeige;
    // --------------- Kunde ---------------
    // ------------ Mitarbeiter ------------
    @FXML
    private TextField mitarbeiter_id;
    @FXML
    private TextField mitarbeiter_vorname;
    @FXML
    private TextField mitarbeiter_nachname;
    @FXML
    private TextField mitarbeiter_lohn;
    @FXML
    private TextField mitarbeiter_beschaeftigungsstart;
    @FXML
    private TextField mitarbeiter_verfuegbarkeit;

    @FXML
    private ComboBox mitarbeiter_anrede;
    @FXML
    private ComboBox mitarbeiter_adresse;

    @FXML
    private Button mitarbeiter_speichern;
    @FXML
    private Button mitarbeiter_anlegen;
    @FXML
    private Button mitarbeiter_suchen;

    @FXML
    private TableView mitarbeiter_anzeige;
    // ------------ Mitarbeiter ------------


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