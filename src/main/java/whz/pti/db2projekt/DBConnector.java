package whz.pti.db2projekt;

import java.sql.*;
import java.util.logging.Logger;

public class DBConnector {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private static String DB_URL;
    private Connection dbConnection;

    public DBConnector(String username, String password) {
        setDBUrl(username,password);
        registerDriver();
    }

    private void setDBUrl(String username, String password) {
        DB_URL = "jdbc:sqlserver://localhost;databaseName=Autohaendler;user="+ username +";password=" + password + ";trustServerCertificate=true;";
        registerDriver();
    }

    private void registerDriver () {
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            logger.info( "SQL-Driver registered");
        } catch(SQLException e) {
            logger.severe("SQL-Driver could not be registered");
            e.printStackTrace();
        }
    }
    public Connection openConnection() {
        try {
            dbConnection = DriverManager.getConnection(DB_URL);
            logger.info("Connection to database established");
        } catch(SQLException e) {
            logger.severe("Error while trying to execute query");
            e.printStackTrace();
        }
        return dbConnection;
    }
}

