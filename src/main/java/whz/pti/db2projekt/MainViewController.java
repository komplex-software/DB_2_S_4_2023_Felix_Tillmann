package whz.pti.db2projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import whz.pti.db2projekt.model.*;

import java.sql.*;
import java.util.ArrayList;

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

            Anrede an1 = Anrede.getAnredeList().get(0);
            an1.setAnredewort("ens");
            updateAnreden(an1);

            Fahrzeug f1 = Fahrzeug.getFahrzeugList().get(0);
            f1.setAnzVorherigeBesitzer(420);
            updateFahrzeuge(f1);

            Fahrzeugfarbe ff1 = Fahrzeugfarbe.getFarbeList().get(0);
            ff1.setFarbname("ANTRAZIT");
            updateFahrzeugfarben(ff1);

            Fahrzeugmodell fm1 = Fahrzeugmodell.getModellList().get(0);
            fm1.setFahrzeugtyp_id(1);
            updateFahrzeugmodelle(fm1);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        showAdresse();
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
            int mietKundeId = rs.getInt("mietKunde_id");
            int kaufKundeId = rs.getInt("kaufKunde_id");

            // Check if mietKunde_id is null and set to -1
            if (rs.wasNull()) {
                mietKundeId = -1;
            }

            // Check if kaufKunde_id is null and set to -1
            if (rs.wasNull()) {
                kaufKundeId = -1;
            }

            Fahrzeug newFahrzeug = new Fahrzeug(
                    rs.getInt("id"),
                    rs.getInt("modell_id"),
                    rs.getFloat("kaufpreis"),
                    rs.getFloat("mietpreis"),
                    rs.getBoolean("istVermietet"),
                    mietKundeId,
                    rs.getBoolean("istVerkauft"),
                    kaufKundeId,
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

    private void updateAnreden(Anrede anrede) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {

            String sql = "UPDATE Anrede SET anredewort = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, anrede.getAnredewort());
            preparedStatement.setInt(2, anrede.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Anrede mit ID " + anrede.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Anrede mit ID " + anrede.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }

        }
    }

    private void updateFahrzeuge(Fahrzeug fahrzeug) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Fahrzeug SET modell_id = ?, kaufpreis = ?, mietpreis = ?, istVermietet = ?, mietKunde_id = ?, istVerkauft = ?, kaufKunde_id = ?, letzterTÜV = ?, anzVorherigeBesitzer = ?, kilometerstand = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, fahrzeug.getModell_id());
                preparedStatement.setFloat(2, fahrzeug.getKaufpreis());
                preparedStatement.setFloat(3, fahrzeug.getMietpreis());
                preparedStatement.setBoolean(4, fahrzeug.isIstVermietet());
                if (fahrzeug.getMietKunde_id() != -1)
                    preparedStatement.setInt(5, fahrzeug.getMietKunde_id());
                else
                    preparedStatement.setNull(5, 1);
                preparedStatement.setBoolean(6, fahrzeug.isIstVerkauft());
                if (fahrzeug.getKaufKunde_id() != -1)
                    preparedStatement.setInt(7, fahrzeug.getKaufKunde_id());
                else
                    preparedStatement.setNull(7, 1);
                preparedStatement.setDate(8, fahrzeug.getLetzterTuev());
                preparedStatement.setInt(9, fahrzeug.getAnzVorherigeBesitzer());
                preparedStatement.setInt(10, fahrzeug.getKilometerstand());
                preparedStatement.setInt(11, fahrzeug.getId());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Fahrzeug mit ID " + fahrzeug.getId() + " erfolgreich aktualisiert.");
                } else {
                    System.out.println("Fahrzeug mit ID " + fahrzeug.getId() + " konnte nicht gefunden werden.");
                }



        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateFahrzeugfarben(Fahrzeugfarbe fahrzeugfarbe) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Fahrzeugfarbe SET farbname = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fahrzeugfarbe.getFarbname());
            preparedStatement.setInt(2, fahrzeugfarbe.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeugfarbe mit ID " + fahrzeugfarbe.getId() + " erfolgreich aktualisiert.");
            } else {
                System.out.println("Fahrzeugfarbe mit ID " + fahrzeugfarbe.getId() + " konnte nicht gefunden werden.");
            }
        } finally {
            // Ressourcen freigeben
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    private void updateFahrzeugmodelle(Fahrzeugmodell fahrzeugmodell) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Fahrzeugmodell SET hersteller_id = ?, fahrzeugtyp_id = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fahrzeugmodell.getHersteller_id());
            preparedStatement.setInt(2, fahrzeugmodell.getFahrzeugtyp_id());
            preparedStatement.setInt(3, fahrzeugmodell.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Fahrzeugmodell with ID " + fahrzeugmodell.getId() + " successfully updated.");
            } else {
                System.out.println("Fahrzeugmodell with ID " + fahrzeugmodell.getId() + " could not be found.");
            }
        } finally {
            // Release resources
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


    private void showAdresse(){
        setUpAdressTable();
        ArrayList<Adresse> listStavaka = Adresse.getAdresseList();
        ObservableList<String> oListStavaka = FXCollections.observableArrayList();
        for (Adresse stavka : listStavaka) {
            oListStavaka.add("heil");
        }

        adresse_anzeige.setItems(oListStavaka);
    }
    private void setUpAdressTable(){
        TableColumn idC = new TableColumn("ID");
        TableColumn straßeC = new TableColumn("Straße");
        TableColumn stadtC = new TableColumn("Stadt");
        TableColumn plzC = new TableColumn("Plz");
        TableColumn hnrC = new TableColumn("Hnr");

        idC.setCellValueFactory(new PropertyValueFactory<>("ID"));
        straßeC.setCellValueFactory(new PropertyValueFactory<>("Straße"));
        stadtC.setCellValueFactory(new PropertyValueFactory<>("Stadt"));
        plzC.setCellValueFactory(new PropertyValueFactory<>("Plz"));
        hnrC.setCellValueFactory(new PropertyValueFactory<>("Hnr"));

        adresse_anzeige.getColumns().addAll(idC, straßeC,stadtC,plzC,hnrC);
    }

}