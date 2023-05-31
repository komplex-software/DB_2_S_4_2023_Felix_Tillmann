package whz.pti.db2projekt;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import whz.pti.db2projekt.model.Mitarbeiter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainViewController {
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

    public void loadConnection() {

        try {

            // Laden der Daten
            loadMitarbeiter(connection);

            // Testen der Daten
            Mitarbeiter.printMitarbeiterCount();

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

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}