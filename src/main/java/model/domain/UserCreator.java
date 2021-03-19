package model.domain;

import cipher.CaesarCipher;
import data_base.account.EmailExistCheckerDb;
import data_base.account.LoginExistCheckerDb;
import data_base.user.UserCreatorDb;
import model.User;
import validate.DataValidatorRegex;

public class UserCreator {
    private String login;
    private String password;
    private String email;
    private LoginExistCheckerDb loginExistCheckerDb = new LoginExistCheckerDb();
    private EmailExistCheckerDb emailExistCheckerDb = new EmailExistCheckerDb();
    private UserCreatorDb userCreatorDb = new UserCreatorDb();

    public UserCreator(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User createUser() {
        String exceptionMessage = validateInputAndCreateExceptionMessage();
        if (exceptionMessage.isEmpty()) {
            String encryptedPassword = CaesarCipher.encryptPassword(password);
            return userCreatorDb.addUser(new User(login, encryptedPassword, email));
        }
        throw new IllegalArgumentException(exceptionMessage);
    }

    private String validateInputAndCreateExceptionMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!DataValidatorRegex.checkLogin(login)) {
            stringBuilder.append("login incorrect\n");
        }
        if (loginExistCheckerDb.isLoginExist(login)) {
            stringBuilder.append("login exist\n");
        }
        if (!DataValidatorRegex.checkPassword(password)) {
            stringBuilder.append("password incorrect\n");
        }
        if (!DataValidatorRegex.checkEmail(email)) {
            stringBuilder.append("email incorrect\n");
        }
        if (emailExistCheckerDb.isEmailExist(email)) {
            stringBuilder.append("email exist\n");
        }
        return stringBuilder.toString();
    }
}
