package com.github.jhonatacustodio.challengeweekproject.domain.controllers.exception;

import com.github.jhonatacustodio.challengeweekproject.domain.exceptionHandler.MessageExceptionHandler;
import com.github.jhonatacustodio.challengeweekproject.domain.exceptionHandler.PartyInvalidFielException;
import com.github.jhonatacustodio.challengeweekproject.domain.exceptionHandler.PartyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "com.github.jhonatacustodio.challendweekproject.domain.controllers")
public class PartyControllerException {
    @ResponseBody
    @ExceptionHandler(PartyNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> partyNotFound(PartyNotFoundException exception){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Party not found"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @ExceptionHandler(PartyInvalidFielException.class)
    public ResponseEntity<MessageExceptionHandler> partyInvalidField(PartyInvalidFielException exception){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Invalid field fill only with (Center, Right, Left)"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentsNotValid(MethodArgumentNotValidException error){
        BindingResult result = error.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder stringBuilder = new StringBuilder("Fields cannot be null");
        for(FieldError fieldError: fieldErrors){
            stringBuilder.append(" | ");
            stringBuilder.append(" -> ");
            stringBuilder.append(fieldError.getField());
            stringBuilder.append(" <- ");
        }
        MessageExceptionHandler msgError = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), stringBuilder.toString()
        );
        return  new ResponseEntity<>(msgError, HttpStatus.BAD_REQUEST);
    }
}
