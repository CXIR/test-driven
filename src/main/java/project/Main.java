package project;

import java.sql.ResultSet;
import java.util.*;

/**
 * Created by Hugo on 09/06/2017.
 */

public class Main {
    public static Database db;
    public static void main(String[] args) {
        db = new Database("//localhost/test-driven", "root", "");

        Scanner scanner = new Scanner(System.in);
        String choice;
        int menu;

        do {
            System.out.print("MENU :\n\n1 - Inscription\n2 - Connexion\n3 - Mot de passe oublié\n4 - Quitter\n\nChoix : ");
            choice = scanner.nextLine();
            try {
                menu = Integer.parseInt(choice);
            } catch(Exception e) {
                System.out.println();
                menu = 0;
            }
            switch(menu) {
                case 1:
                    signIn();
                    break;
                case 2:
                    //logIn();
                    new Connection();
                    /*
                    * Apres le login bdd mettre un autre menu genre changer d'adresse email + deconnexion pour pouvoir reboucler ici
                     */
                    break;
                case 3:
                    resetPassword();
                    break;
            }
        }
        while(menu != 4);

    }

    public static void signIn() {
        Verification verification = new Verification();
        Scanner scanner = new Scanner(System.in);
        String email, password, password2; // Console readPassword si on veut pas afficher les mdp en clair
        boolean result;
        do {
            System.out.println("\nVeuillez saisir votre adresse email : ");
            email = scanner.nextLine();
            result = verification.isValidEmail(email);
            if(result == false) {
                System.out.println("\nErreur merci de saisir une adresse email valide.");
            }
        } while(result == false);
        do {
            System.out.println("\nVeuillez saisir votre mot de passe : ");
            password = scanner.nextLine();
            result = verification.isValidPassword(password);
            if(result == false) {
                System.out.println("\nErreur merci de saisir un mot de passe entre 8 et 25 caractères dont au moins 1 chiffre.");
            } else {
                System.out.println("\nVeuillez confirmer votre mot de passe : ");
                password2 = scanner.nextLine();
                if(password.equals(password2)) {
                    result = true;
                } else {
                    System.out.println("\nErreur merci de saisir deux mots de passe identiques.");
                    result = false;
                }
            }
        } while(result == false);
        User user = new User(email, password);
        /*if(user.insertIntoDB()) {
            System.out.println("\nFélicitation vous êtes désormais inscrit(e) ! \n");
        } else {
            System.out.println("\nErreur mysql veillez à lancer votre serveur et/ou installer le driver JDBC.\n");
        }*/
        if(db.connect()){
            if(db.updateValue("INSERT INTO user (email, password) VALUES('" + user.get_email() + "','" + user.get_password() + "');")) {
                System.out.println("\nFélicitation vous êtes désormais inscrit(e) ! \n");
            } else {
                System.out.println("\nErreur.\n");
            }
        }
        else{
            System.out.println("\nErreur mysql veillez à lancer votre serveur et/ou installer le driver JDBC.\n");
        }
    }

    /*public static void logIn() {
        System.out.println("\nConnexion\n");
        Verification verification = new Verification();
        Scanner scanner = new Scanner(System.in);
        String email, password;
        boolean result;
        do {
            System.out.println("\nVeuillez saisir votre adresse email : ");
            email = scanner.nextLine();
            result = verification.isValidEmail(email);
            if(result == false) {
                System.out.println("\nErreur merci de saisir une adresse email valide.");
            }
        } while(result == false);
        do {
            System.out.println("\nVeuillez saisir votre mot de passe : ");
            password = scanner.nextLine();
            result = verification.isValidPassword(password);
            if(result == false) {
                System.out.println("\nErreur merci de saisir un mot de passe entre 8 et 25 caractères dont au moins 1 chiffre.");
            }
        } while(result == false);
        User user = new User(email, password);

        System.out.println("\nConnexion a la base de données...\n");

        if(db.connect()){
            ResultSet res = db.getResultOf("SELECT email, password FROM user WHERE email = '" + user.get_email() + "' AND password ='" + user.get_password() + "';");
            boolean found = false;
            try{
                while (res.next()) {
                    if(res.getString("email").equals(user.get_email()) || res.getString("password").equals(user.get_password())){
                        found = true;
                    }
                }
                if(found){
                    System.out.println("\nVous êtes connecté(e) ! \n");
                } else{
                    System.out.println("\nIdentifiants invalides ! \n");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("\nErreur.\n");
            }
        }
        else{
            System.out.println("\nErreur mysql veillez à lancer votre serveur et/ou installer le driver JDBC.\n");
        }
    }*/

    public static void resetPassword() {

        System.out.println("\nMot de passe oublié\n");

    }

}