package app.test.service;

import app.test.dto.User;
import app.test.model.Response;
import app.test.model.ResultCode;
import app.test.storage.JdbcUserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements UserService {

    JdbcUserRepository userRepository;

    public CreateUserService(JdbcUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response apply(User user) {
        Response response = new Response();
        userRepository.createUser(user);
        response.setResultCode(ResultCode.OK.getCode());
        return response;
    }
}
