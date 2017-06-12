/**
 *
 */
package testProject;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import project.Connection;
import project.Database;

/**
 * @author davy
 *
 */
public class ConnectionTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {


    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link project.Connection#connect(java.lang.String, java.lang.String)}.
     * test la methode connect avec une adresse valide et motdepasse valide
     */
    @Test

    public void testConnect() {

        // la methode connect utilise 2 scanner et scaner lit ligne par ligne
        // on va donc initialiser system.in pour simuler une saisie (separer par
        // \n pour le metre sur plusieur ligne

        String input = "davy@gmail.com\nmotdepasse123"; // adresse valide + mot
        // de
        // passe
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // insertion dans system.in

        Connection c = new Connection();
        c.connect();
        String tester = c.getUser(); // email+mot de passe en md5
        System.out.println(tester);
        assertTrue("connect ok", tester.equals("davy@gmail.com5504b4f70ca78f97137ff8ad5f910248"));
        // fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link project.Connection#connect(java.lang.String, java.lang.String)}.
     * test la methode connect avec une adresse non valide et motdepasse valide
     */
    @Test

    public void testConnect_wrong_email() {

        // la methode connect utilise 2 scanner et scaner lit ligne par ligne
        // on va donc initialiser system.in pour simuler une saisie (separer par
        // \n pour le metre sur plusieur ligne

        String input = "davygmail.com\ndavy@gmail.com\nmotdepasse123"; // adresse
        // non
        // valide
        // +
        // adresse
        // valide
        // +mot
        // de
        // passe
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // insertion dans system.in

        Connection c = new Connection();
        c.connect();
        String tester = c.getUser(); // email+mot de passe en md5
        System.out.println(tester);
        assertTrue("connect wrong email ok", tester.equals("davy@gmail.com5504b4f70ca78f97137ff8ad5f910248"));
        // fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link project.Connection#connect(java.lang.String, java.lang.String)}.
     * test la methode connect avec une adresse valide et motdepasse non valide
     */
    @Test

    public void testConnect_wrong_pass_nb_char() {

        // la methode connect utilise 2 scanner et scaner lit ligne par ligne
        // on va donc initialiser system.in pour simuler une saisie (separer par
        // \n pour le metre sur plusieur ligne

        String input = "davy@gmail.com\nmmm1\nmotdepasse123"; // adresse valide
        // + mdp valide
        // +mot de
        // passe
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // insertion dans system.in

        Connection c = new Connection();
        c.connect();
        String tester = c.getUser(); // email+mot de passe en md5
        System.out.println(tester);
        assertTrue("connect wrong pass ok", tester.equals("davy@gmail.com5504b4f70ca78f97137ff8ad5f910248"));
        // fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link project.Connection#connect(java.lang.String, java.lang.String)}.
     * test la methode connect avec une adresse valide et motdepasse non valide
     */
    @Test
    public void testConnect_wrong_pass_no_number() {

        // la methode connect utilise 2 scanner et scaner lit ligne par ligne
        // on va donc initialiser system.in pour simuler une saisie (separer par
        // \n pour le metre sur plusieur ligne

        String input = "davy@gmail.com\nmmmjfjfjfjfjfjf\nmotdepasse123"; // adresse
        // valide
        // +
        // mdp
        // valide
        // +mot
        // de
        // passe
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // insertion dans system.in

        Connection c = new Connection();
        c.connect();
        String tester = c.getUser(); // email+mot de passe en md5
        System.out.println(tester);
        assertTrue("connect wrong pass not number ok", tester.equals("davy@gmail.com5504b4f70ca78f97137ff8ad5f910248"));
        // fail("Not yet implemented");
    }

    /**
     * Test method for {@link project.Connection#isLogsValid()}.
     */
    @Test
    public void testIsLogsValid() {
        Database db1 = new Database("//localhost/test-driven_db", "root", "root");

        String input = "davy@gmail.com\nmotdepasse123"; // adresse valide + mdp
        // valide +mot de
        // passe
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // insertion dans system.in
        Connection c = new Connection();
        c.setDb(db1);
        c.connect();

        assertTrue("islog valide ",c.isLogsValid());


    }

}
