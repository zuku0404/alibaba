package data_base.user;

import model.dao.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);
    void removeUser (User user);
    Optional<User> findUserByLogin(String login);
    User updateUser (User user, String firstName, String secondName,int age);
    User updatePassword (User user,String password);
}
