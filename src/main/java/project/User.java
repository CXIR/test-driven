package project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.*;
import java.sql.Connection;

/**
 * Created by Hugo on 09/06/2017.
 */
public class User {

    private String email;
    private String password;

    public User(String email, String password) {

        this.email = email;
        this.password = encryptPassword(password);

    }

    public String get_email() {

        return this.email;

    }

    public void set_email(String email) {

        this.email = email;

    }

    public String get_password() {

        return this.password;

    }

    public void set_password(String password) {

        this.password = password;

    }

    public static String encryptPassword(String password) {

        String newPassword = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            byte[] bytes = md5.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            newPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return newPassword;

    }

    public boolean insertIntoDB() {

        String cryptedPassword = encryptPassword(this.password);
        String url = "jdbc:mysql://localhost/test-driven";
        String log = "root";
        String pass = "";
        ResultSet res;
        ResultSetMetaData count;
        Connection connexion = null;
        Statement statement = null;
        PreparedStatement prepare = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, log, pass);
            statement = connexion.createStatement();
            String request = "INSERT INTO user SET email = ? AND password = ?";
            prepare = connexion.prepareStatement(request);
            prepare.setString(1, this.email);
            prepare.setString(2, cryptedPassword);
            res = prepare.executeQuery();
            res.next();
            count = res.getMetaData();
            if(count.getColumnCount() == 1) {
                return true;
            } else {
                return false;
            }
        } catch(SQLException e) {
            System.out.println();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println();
            e.printStackTrace();
        } finally {
            try {
                connexion.close();
                prepare.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println();
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updatePassword(String newPassword) {

        String cryptedPassword = encryptPassword(this.password);

        String url = "jdbc:mysql://localhost/test-driven";
        String log = "root";
        String pass = "root";
        ResultSet res;
        ResultSetMetaData count;
        Connection connexion = null;
        Statement statement = null;
        PreparedStatement prepare = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, log, pass);
            statement = connexion.createStatement();
            String request = "UPDATE user SET password = ? WHERE email = ?";
            prepare = connexion.prepareStatement(request);
            prepare.setString(1, cryptedPassword);
            prepare.setString(2, this.email);
            res = prepare.executeQuery();
            res.next();
            count = res.getMetaData();
            if(count.getColumnCount() == 1) {
                return true;
            } else {
                return false;
            }
        } catch(SQLException e) {
            System.out.println();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println();
            e.printStackTrace();
        } finally {
            try {
                connexion.close();
                prepare.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println();
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean isUserValid(){
        String cryptedPassword = encryptPassword(this.password);
        String url = "jdbc:mysql://localhost/test-driven";
        String log = "root";
        String pass = "";
        ResultSet res;
        ResultSetMetaData count;
        Connection connexion = null;
        Statement statement = null;
        PreparedStatement prepare = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, log, pass);
            statement = connexion.createStatement();
            String request = "SELECT user WHERE email = ? AND password = ?";
            prepare = connexion.prepareStatement(request);
            prepare.setString(1, this.email);
            prepare.setString(2, cryptedPassword);
            res = prepare.executeQuery();
            res.next();
            count = res.getMetaData();
            if(count.getColumnCount() == 1) {
                return true;
            } else {
                return false;
            }
        } catch(SQLException e) {
            System.out.println();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println();
            e.printStackTrace();
        } finally {
            try {
                connexion.close();
                prepare.close();
                statement.close();
            } catch (SQLException e) {
                System.out.println();
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println();
                e.printStackTrace();
            }
        }
        return false;
    }

    public String toString() {

        return this.email+" "+this.password;

    }



}
