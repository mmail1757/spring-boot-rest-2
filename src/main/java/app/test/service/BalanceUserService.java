package app.test.service;

import app.test.dto.User;
import app.test.exceptions.InvalidPasswordException;
import app.test.model.ExtraTag;
import app.test.model.Response;
import app.test.model.ResultCode;
import app.test.storage.JdbcUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class BalanceUserService implements UserService {

    JdbcUserRepository userRepository;

    public BalanceUserService(JdbcUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Response apply(User user) {
        User dbUser = userRepository.getUserByLogin(user.getLogin());
        Response response = new Response();
        if (dbUser.getPassword().equals(user.getPassword())) {
            response.setResultCode(ResultCode.OK.getCode());
            response.setExtraTags(new ArrayList<>());
            ExtraTag tag = new ExtraTag("balance", dbUser.getBalance().toString());
            response.getExtraTags().add(tag);
            return response;
        }
        throw new InvalidPasswordException();
    }
}
