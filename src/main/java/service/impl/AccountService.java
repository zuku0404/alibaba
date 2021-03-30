package service.impl;

import cipher.CaesarCipher;
import model.dao.User;

import java.util.Optional;

public class AccountService {

    public User logIntoAccount(String login, String password) {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        Optional<User> userByLogin = userServiceImpl.findUserByLogin(login);
        if (userByLogin.isPresent()) {
            User user = userByLogin.get();
            return checkPassword(user,password);
        }
        throw new IllegalArgumentException("login is incorrect");
    }

    private User checkPassword(User user,String password) {
        String encryptedPassword = user.getPassword();
        String decryptedPassword = CaesarCipher.decryptPassword(encryptedPassword);
        if(decryptedPassword.equals(password)){
            return user;
        }
        throw new IllegalArgumentException("password is incorrect");
    }

}
