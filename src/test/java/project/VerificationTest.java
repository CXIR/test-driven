package project;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ludwigroger on 12/06/2017.
 */

public class VerificationTest {
    Verification verif = new Verification();

    @Test
    public void isValidEmail() throws Exception {
        String mail_should_be_valid = "roger.ludwig@ymail.com";
        String mail_should_be_invalid = "roger.ludwig.ymail.com";

        Assert.assertEquals(true,verif.isValidEmail(mail_should_be_valid));
        Assert.assertEquals(false,verif.isValidEmail(mail_should_be_invalid));
    }

    @Test
    public void isValidPassword() throws Exception {
        String pass_should_be_valid = "Demo1234";
        String pass_should_be_invalid = "Demo123";

        Assert.assertEquals(true,verif.isValidPassword(pass_should_be_valid));
        Assert.assertEquals(false,verif.isValidPassword(pass_should_be_invalid));
    }

}