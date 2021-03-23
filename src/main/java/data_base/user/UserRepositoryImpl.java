package data_base.user;

import model.dao.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User addUser(User user) {
        UserCreatorDb userCreatorDb = new UserCreatorDb();
        return userCreatorDb.addUser(user);
    }

    @Override
    public void removeUser(User user) {
        UserRemoverDb userRemoverDb = new UserRemoverDb();
        userRemoverDb.removeUser(user);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        UserFinderDb userFinderDb = new UserFinderDb();
       return userFinderDb.findUserByLogin(login);
    }

    @Override
    public User updateUser(User user, String firstName, String secondName, int age) {
        UserEditorDb userEditorDb = new UserEditorDb();
        return userEditorDb.updateUser(user,firstName,secondName,age);
    }

    @Override
    public User updatePassword(User user, String password) {
        UserEditorDb userEditorDb = new UserEditorDb();
        return userEditorDb.updatePassword(user,password);
    }
}



