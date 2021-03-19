package validate;

import java.util.regex.Pattern;

public class DataValidatorRegex { //TODO
    private DataValidatorRegex(){}

    public static boolean checkLogin (String login) {
//        String regex = "(?=.*?[A-Z])\\S{8,}";
//        return Pattern.matches(regex, login);
        return true;
    }
    public static boolean checkPassword (String password){
//        String regex = "^(?=.*?[A-Z])(?=.*?\\d)(?=.*?[a-z])(?=.*[$@!%*?&.])\\S{8,}";
//        return Pattern.matches(regex,password);
        return true;
    }
    public static boolean checkEmail (String email){
//        String regex = "";
//        return Pattern.matches(regex,email);
        return true;
    }
    public static boolean checkPostTitle (String postTitle){
//        String regex = "";
//        return Pattern.matches(regex,email);
        return true;
    }
    public static boolean checkPostContent (String postContent){
//        String regex = "";
//        return Pattern.matches(regex,email);
        return true;
    }


}
