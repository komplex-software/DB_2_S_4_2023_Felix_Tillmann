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

    private UserPermissions perms;
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

        DBConnector dbConnector = new DBConnector("sa",checkPassword());
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

            checkPermissions(controller);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();





        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

   private void checkPermissions(MainViewController cont){
       //mitgeben der Berechtigungen

       String usernamen = username.getSelectionModel().getSelectedItem().toString();
       switch (usernamen){
           case "sa":
               perms = UserPermissions.ADMIN;
               System.out.println("admin");
               break;
           case "readwriter":
               perms = UserPermissions.READWRITE;
               System.out.println("RW");
               break;
           case "reader":
               perms = UserPermissions.READ;
               System.out.println("R");
               break;
           default:
               perms = UserPermissions.READ;
               System.out.println("S");
       }
       System.out.println(perms.toString());
       cont.setAcces(perms);
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