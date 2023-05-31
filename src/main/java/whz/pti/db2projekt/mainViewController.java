package whz.pti.db2projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import whz.pti.db2projekt.model.Mitarbeiter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mainViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void initialize() {
        DBConnector dbConnector = new DBConnector();
        try {
            Connection connection = dbConnector.openConnection();

            // Laden
            loadMitarbeiter(connection);

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
                    rs.getDate("Beschaeftigung"),
                    rs.getBoolean("Verfuegbar")
                    );
            Mitarbeiter.addMitarbeiter(newMitarbeiter);
        }
    }
}