package whz.pti.db2projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import whz.pti.db2projekt.model.UserPermissions;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginViewController {

    @FXML
    private ComboBox username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    private ObservableList<String> olUsernames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        olUsernames.add("sa");          // Admin: 0
        olUsernames.add("reader");      // Reader: 1
        olUsernames.add("readwriter");   // Writer: 2

        username.setItems(olUsernames);

        //setup zum testen
        username.getSelectionModel().select(0);
        password.setText("ms-SQL-2022");
    }

    @FXML
    protected void onLoginButtonClick() {
        if (checkPassword().equals("falsch")){return;}

        DBConnector dbConnector = new DBConnector(username.getValue().toString(),checkPassword());
        Connection connection = dbConnector.openConnection();

        // Startet Main View
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("main-view.fxml"));

            Parent root = (Parent)fxmlLoader.load();
            MainViewController controller = fxmlLoader.<MainViewController>getController();

            // mitgeben der Connection
            controller.setConnection(connection);
            controller.loadConnection();

            //mitgeben der Berechtigungen
            UserPermissions perms;
            if (username.getSelectionModel().getSelectedIndex() == 0)
                perms = UserPermissions.ADMIN;
            else if (username.getSelectionModel().getSelectedIndex() == 1)
                perms = UserPermissions.READ;
            else
                perms = UserPermissions.READWRITE;
            controller.setPermissions(perms);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
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