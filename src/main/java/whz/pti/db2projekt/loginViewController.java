package whz.pti.db2projekt;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;

public class loginViewController {

    @FXML
    private ComboBox username;
    private TextField password;
    private Button login;

    private ObservableList<String> olUsernames = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        olUsernames.add("sa");
        olUsernames.add("reiter");
        olUsernames.add("schreiter");
        username.setItems(olUsernames);
        username.getSelectionModel().select(0);
    }

    @FXML
    protected void onLoginButtonClick() {

        DBConnector dbConnector = new DBConnector(username.getValue().toString(),"ms-SQL-2022");
        try {
            Connection connection = dbConnector.openConnection();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


// aktuell nicht in use
    private String checkPassword(String password){
        if(password.equals("test")){
            return "ms-SQL-2022";
        }
            return "ms-SQL-2022";
    }
}