package com.github.jhonatacustodio.challengeweekproject.domain.controllers.exception;

import com.github.jhonatacustodio.challengeweekproject.domain.exceptionHandler.AssociatePartyNotFoundException;
import com.github.jhonatacustodio.challengeweekproject.domain.exceptionHandler.MessageExceptionHandler;
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
public class AssociatePartyControllerException {
    @ResponseBody
    @ExceptionHandler(AssociatePartyNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> partyNotFound(AssociatePartyNotFoundException exception){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Associate party not found"
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
