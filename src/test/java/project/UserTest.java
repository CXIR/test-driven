package project;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by ludwigroger on 12/06/2017.
 */

public class UserTest {

    User user = new User("roger.ludwig@ymail.com","Demo1234");

    @Test
    public void should_get_email() throws Exception {
        String mail_test = user.get_email();
        Assert.assertEquals("roger.ludwig@ymail.com",mail_test);
    }

    @Test
    public void should_set_email() throws Exception {
        String new_mail_test = "ludwig.roger@outlook.com";
        user.set_email(new_mail_test);
        Assert.assertEquals("ludwig.roger@outlook.com",user.get_email());
    }

    @Test
    public void should_get_password() throws Exception {
        String pass_test = user.get_password();
        Assert.assertEquals("Demo1234",pass_test);
    }

    @Test
    public void should_set_password() throws Exception {
        String new_pass_test = "Demo5678";
        user.set_password(new_pass_test);
        Assert.assertEquals("Demo5678",user.get_password());
    }

    @Test
    public void should_encryptPassword() throws Exception {

    }

    @Test
    public void insertIntoDB() throws Exception {

    }

}