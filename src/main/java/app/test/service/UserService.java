package app.test.service;

import app.test.dto.User;
import app.test.model.Response;

public interface UserService {

    Response apply(User user);

}
