package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mickael.afonso on 06/10/2017.
 */

public class Database {
    private String      dbName;
    private  Connection  connection;
    private Statement   requete;
    private String user;
    private String password;

    public Database (String dbName, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e1) {
            System.err.println(e1.getMessage());
        }

        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.connection = null;
    }

    public boolean connect () {
        try {
            connection = DriverManager.getConnection("jdbc:mysql:" + this.dbName, user, password);
            requete = connection.createStatement();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean disconnect () {
        try {
            if(connection != null)
                connection.close();

            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getResultOf (String requete) {
        try {
            return this.requete.executeQuery(requete);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateValue (String requete) {
        try {
            this.requete.executeUpdate(requete);
            return true;
        }
        catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}