package project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.*;
import java.sql.Connection;

/**
 * Created by Hugo on 09/06/2017.
 */
public class User {
    private Database db;
    private String email;
    private String password;

    public User(String email, String password) {

        this.email = email;
        this.password = encryptPassword(password);
        this.db = new Database("//localhost/test-driven_db", "root", "root");

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

        this.password = encryptPassword(password);

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

    public boolean insertIntoDB(){
        if(db.connect()){
            String query = "INSERT INTO user(email,password) VALUES('"+this.email+"','"+this.password+"')";
            if(db.updateValue(query)) {
                return true;
            }
        }
        return false;
    }

    public boolean updatePassword(String new_password){
        String pass = encryptPassword(new_password);
        if(db.connect()){
            String query = "UPDATE user SET password = '"+pass+"' WHERE email = '"+this.email+"'";
            if(db.updateValue(query)) {
                this.password = pass;
                return true;
            }
        }
        return false;
    }

    public String toString() {

        return this.email+" "+this.password;

    }



}
