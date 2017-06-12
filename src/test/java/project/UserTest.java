package project;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;

/**
 * Created by ludwigroger on 12/06/2017.
 */

public class UserTest {
    User user = new User("roger.ludwig@yamil.com","Demo1234");
    Database db = new Database("//localhost/test-driven_db", "root", "root");

    @Test
    public void get_email() throws Exception {
        Assert.assertEquals("roger.ludwig@yamil.com",user.get_email());
    }

    @Test
    public void set_email() throws Exception {
        user.set_email("ludwig.roger@outlook.com");
        Assert.assertEquals("ludwig.roger@outlook.com",user.get_email());
    }

    @Test
    public void get_password() throws Exception {
        Assert.assertEquals(user.encryptPassword("Demo1234"),user.get_password());
    }

    @Test
    public void set_password() throws Exception {
        user.set_password("Demo5678");
        Assert.assertEquals(user.encryptPassword("Demo5678"),user.get_password());
    }


    @Test
    public void insertIntoDB() throws Exception {
        Assert.assertEquals(true,user.insertIntoDB());
    }

    @Test
    public void updatePassword() throws Exception {
        String new_pass = "Demo8907";
        Assert.assertEquals(true,user.updatePassword(new_pass));
    }

}