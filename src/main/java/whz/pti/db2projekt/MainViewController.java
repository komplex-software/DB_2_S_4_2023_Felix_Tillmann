package whz.pti.db2projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import whz.pti.db2projekt.model.Kunde;
import whz.pti.db2projekt.model.Mitarbeiter;
import whz.pti.db2projekt.model.UserPermissions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
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