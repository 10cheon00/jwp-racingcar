package racingcar.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import racingcar.exception.ErrorMessage;
import racingcar.exception.InvalidFormException;

@RestControllerAdvice
public class RacingGameExceptionHandler {
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormException.class)
    public ErrorMessage error() {
        return new ErrorMessage("Invalid form error");
    }
}
