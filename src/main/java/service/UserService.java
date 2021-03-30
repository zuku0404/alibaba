package service;

import model.dao.User;

import java.util.Optional;

public interface UserService {
    User createUser(String login, String password, String email);

    void removeUser (User user);

    Optional<User> findUserByLogin(String login);
}
