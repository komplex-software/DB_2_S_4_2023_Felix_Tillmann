package whz.pti.db2projekt;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import whz.pti.db2projekt.model.*;

import java.sql.*;

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

            // Testen update
            Mitarbeiter m1 = Mitarbeiter.getMitarbeiterList().get(0);
            m1.setVorname("TEST");
            updateMitarbeiter(m1);

            Hersteller h1 = Hersteller.getHerstellerList().get(0);
            h1.setName("TEST");
            updateHersteller(h1);

            Adresse a1 = Adresse.getAdresseList().get(0);
            a1.setStadt("TEST");
            updateAdressen(a1);


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
                    rs.getInt("ID"),
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

    // TODO:
    // return in allen updates falls keine write permissions

    private void updateHersteller(Hersteller hersteller) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Hersteller SET name = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hersteller.getName());
            preparedStatement.setInt(2, hersteller.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Hersteller mit ID " + hersteller.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Hersteller mit ID " + hersteller.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateMitarbeiter(Mitarbeiter mitarbeiter) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE Mitarbeiter SET Vorname = ?, Nachname = ?, Adresse_ID = ?, Anrede_ID = ?, Lohn = ?, BeschäftigungsStart = ?, Verfügbar = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mitarbeiter.getVorname());
            preparedStatement.setString(2, mitarbeiter.getNachname());
            preparedStatement.setInt(3, mitarbeiter.getAdresse_id());
            preparedStatement.setInt(4, mitarbeiter.getAnrede_id());
            preparedStatement.setFloat(5, mitarbeiter.getLohn());
            preparedStatement.setDate(6, mitarbeiter.getBeschaeftigungsstart());
            preparedStatement.setBoolean(7, mitarbeiter.isVerfuegbar());
            preparedStatement.setInt(8, mitarbeiter.getId());


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mitarbeiter mit ID " + mitarbeiter.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Mitarbeiter mit ID " + mitarbeiter.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateAdressen(Adresse adresse) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE Adresse SET straße = ?, stadt = ?, postleitzahl = ?, hausnummer = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adresse.getStrasse());
            preparedStatement.setString(2, adresse.getStadt());
            preparedStatement.setString(3, adresse.getPostleitzahl());
            preparedStatement.setString(4, adresse.getHausnummer());
            preparedStatement.setInt(5, adresse.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Adresse mit ID " + adresse.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Adresse mit ID " + adresse.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }
}