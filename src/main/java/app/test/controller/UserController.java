package app.test.controller;

import app.test.dto.User;
import app.test.exceptions.TechnicalErrorException;
import app.test.model.*;
import app.test.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/")
public class UserController {

    final UserService createUserService;

    final UserService balanceUserService;

    public UserController(UserService createUserService, UserService balanceUserService) {
        this.createUserService = createUserService;
        this.balanceUserService = balanceUserService;
    }

    @PostMapping(value = "/user", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Response> AddUser(@RequestBody Request request) {
        Response response;
        final User user = new User(request.getExtraTags().stream().filter(item -> "login".equals(item.getName())).findFirst().orElse(new ExtraTag()).getValue(),
                request.getExtraTags().stream().filter(item -> "password".equals(item.getName())).findFirst().orElse(new ExtraTag()).getValue(),
                BigDecimal.ZERO);
        if (RequestType.CREATE_AGT.getCode().equals(request.getRequestType())) {
            response = createUserService.apply(user);
        } else if (RequestType.GET_BALANCE.getCode().equals(request.getRequestType())) {
            response = balanceUserService.apply(user);
        } else {
            throw new TechnicalErrorException("Not supported operation");
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_XML).body(response);
    }


}