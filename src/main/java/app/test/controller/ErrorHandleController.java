package app.test.controller;

import app.test.exceptions.InvalidPasswordException;
import app.test.exceptions.TechnicalErrorException;
import app.test.exceptions.UserAlreadyExistsException;
import app.test.exceptions.UserNotFoundException;
import app.test.model.Response;
import app.test.model.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandleController implements ErrorController {

    @ExceptionHandler({ UserAlreadyExistsException.class })
    public ResponseEntity<Response> userAlreadyExistsHandler(final Exception e) {
        Response response= new Response();
        response.setResultCode(ResultCode.USER_ALREADY_EXISTS.getCode());
        ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_XML).body(response);
        return entity;
    }

    @ExceptionHandler({ TechnicalErrorException.class, Exception.class })
    public ResponseEntity<Response> technicalErrorHandler(final Exception e) {
        Response response= new Response();
        response.setResultCode(ResultCode.TECHNICAL_ERROR.getCode());
        ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_XML).body(response);
        return entity;
    }

    @ExceptionHandler({ InvalidPasswordException.class })
    public ResponseEntity<Response> invalidPasswordHandler(final Exception e) {
        Response response= new Response();
        response.setResultCode(ResultCode.INVALID_PASSWORD.getCode());
        ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_XML).body(response);
        return entity;
    }

    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<Response> userNotFoundHandler(final Exception e) {
        Response response= new Response();
        response.setResultCode(ResultCode.USER_NOT_FOUND.getCode());
        ResponseEntity entity = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_XML).body(response);
        return entity;
    }
}
