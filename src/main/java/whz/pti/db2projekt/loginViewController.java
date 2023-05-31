package whz.pti.db2projekt;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import whz.pti.db2projekt.model.Mitarbeiter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginViewController {

    @FXML
    private ComboBox username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;

    private ObservableList<String> olUsernames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        olUsernames.add("sa");
        olUsernames.add("reiter");
        olUsernames.add("schreiter");

        username.setItems(olUsernames);

        //setup zum testen
        username.getSelectionModel().select(0);
        password.setText("ms-SQL-2022");
    }

    @FXML
    protected void onLoginButtonClick() {
        checkPassword();
        DBConnector dbConnector = new DBConnector(username.getValue().toString(),checkPassword());
        try {
            Connection connection = dbConnector.openConnection();
            connection.close();

            // Startet Main View
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("main-view.fxml"));
                /*
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("Datenbanktool");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String checkPassword(){
        if (password.getText().equals("ms-SQL-2022")){
            return "ms-SQL-2022";
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("E Rohr");
            alert.setHeaderText("Passwort oder Nutzername falsch");
            alert.setContentText("bitte 'test' eingeben");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }

        return "falsch";
    }
}