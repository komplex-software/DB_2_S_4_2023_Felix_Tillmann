module whz.pti.db2projekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens whz.pti.db2projekt to javafx.fxml;
    exports whz.pti.db2projekt;
}