package service.impl;

import cipher.CaesarCipher;
import data_base.account.AccountChecker;
import data_base.user.UserRepositoryImpl;
import model.dao.User;
import validate.DataValidatorRegex;

import java.util.Optional;

public class UserServiceImpl {
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();

    public User createUser(String login, String password, String email) {
        checkCondition(login, password, email);
        String encryptedPassword = CaesarCipher.encryptPassword(password);
        return userRepository.saveUser(new User(login, encryptedPassword, email));
    }

    public void removeUser (User user) {
        PostServiceImpl postServiceImpl = new PostServiceImpl();
        postServiceImpl.getPostByUserId(user.getId())
        .forEach(post -> postServiceImpl.deletePost(post,user));
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
        AccountChecker accountChecker = new AccountChecker();
        StringBuilder stringBuilder = new StringBuilder();
        if (!DataValidatorRegex.checkLogin(login)) {
            stringBuilder.append("login incorrect\n");
        }
        if (accountChecker.isLoginExist(login)) {
            stringBuilder.append("login exist\n");
        }
        if (!DataValidatorRegex.checkPassword(password)) {
            stringBuilder.append("password incorrect\n");
        }
        if (!DataValidatorRegex.checkEmail(email)) {
            stringBuilder.append("email incorrect\n");
        }
        if (accountChecker.isEmailExist(email)) {
            stringBuilder.append("email exist\n");
        }
        return stringBuilder.toString();
    }
}
