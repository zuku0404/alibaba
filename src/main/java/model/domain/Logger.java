package model.domain;

import cipher.CaesarCipher;
import model.User;
import java.util.Optional;
import data_base.account.UserLoginCheckerDb;


public class Logger {
    private String login;
    private String password;
    private UserLoginCheckerDb userLoginCheckerDb = new UserLoginCheckerDb();

    public Logger(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public User logIntoAccount() {
        Optional<User> userByLogin = userLoginCheckerDb.findUserByLogin(login);
        if (userByLogin.isPresent()) {
            User user = userByLogin.get();
            return checkPassword(user);
        }
        throw new IllegalArgumentException("login is incorrect");
    }

    private User checkPassword(User user) {
        String encryptedPassword = user.getPassword();
        String decryptedPassword = CaesarCipher.decryptPassword(encryptedPassword);
        if(decryptedPassword.equals(password)){
            return user;
        }
        throw new IllegalArgumentException("password is incorrect");
    }
}