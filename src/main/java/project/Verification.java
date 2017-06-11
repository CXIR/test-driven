package project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hugo on 09/06/2017.
 */
public class Verification {

    public Verification() {

    }

    public boolean isValidEmail(String email) {
        String reg = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = java.util.regex.Pattern.compile(reg);
        Matcher match = pattern.matcher(email);
        return match.matches();
    }

    public boolean isValidPassword(String password) {
        String reg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,25}$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(reg);
        java.util.regex.Matcher match = pattern.matcher(password);
        return match.matches();
    }

}
