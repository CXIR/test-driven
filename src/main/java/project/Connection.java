package project;

import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Micka on 10/06/2017.
 */
public class Connection implements Authentication {
    private Database db;
    private User user;

    public Connection(){
        this.db = Main.db;
        connect();
        isLogsValid();

    }

    public void connect() {

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
        user = new User(email, password);
    }

    public boolean isLogsValid() {
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
                    return true;
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
        return false;
    }
}
