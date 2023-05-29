package whz.pti.db2projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.SQLException;

public class HelloController {
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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}