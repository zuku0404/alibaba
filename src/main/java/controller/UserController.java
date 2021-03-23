package controller;

import cipher.CaesarCipher;
import data_base.account.EmailExistCheckerDb;
import data_base.account.LoginExistCheckerDb;
import data_base.user.UserRepositoryImpl;
import model.dao.User;
import validate.DataValidatorRegex;

import java.util.Optional;

public class UserController {
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    public User createUser(String login, String password, String email) {
        checkCondition(login, password, email);
        String encryptedPassword = CaesarCipher.encryptPassword(password);
        return userRepository.addUser(new User(login, encryptedPassword, email));
    }

    public void removeUser (User user) {
        PostController postController = new PostController();
        postController.findByUserId(user.getId())
        .forEach(post -> postController.deletePost(post,user));
        userRepository.removeUser(user);
    }
    public Optional<User> findUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }


    private void checkCondition(String login, String password, String email) {
        String exceptionMessage = validateInputAndCreateExceptionMessage(login,password, email );
        if (!exceptionMessage.isEmpty())
            throw new IllegalArgumentException(exceptionMessage);
    }

    private String validateInputAndCreateExceptionMessage(String login,String password, String email) {
        LoginExistCheckerDb loginExistCheckerDb = new LoginExistCheckerDb();
        EmailExistCheckerDb emailExistCheckerDb = new EmailExistCheckerDb();
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
