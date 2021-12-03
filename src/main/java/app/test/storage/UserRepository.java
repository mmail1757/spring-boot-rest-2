package app.test.storage;

import app.test.dto.User;

public interface UserRepository {
    User getUserByLogin(String login);
    void createUser(User user);
}
