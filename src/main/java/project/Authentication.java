package project;

/**
 * Created by Micka on 10/06/2017.
 * A utiliser pour les tests : Davy
 */
public interface Authentication {
    public void connect(String log, String pass);

    public boolean isLogsValid();
}
