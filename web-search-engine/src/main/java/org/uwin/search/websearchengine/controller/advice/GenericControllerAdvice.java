package org.uwin.search.websearchengine.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.uwin.search.websearchengine.exception.IllegalArgumentException;
import org.uwin.search.websearchengine.exception.MalformedUrlException;
import org.uwin.search.websearchengine.exception.base.SearchEngineException;
import org.uwin.search.websearchengine.model.Error;

@RestControllerAdvice
public class GenericControllerAdvice {

    @ExceptionHandler(MalformedUrlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleMalformedUrlException(MalformedUrlException ex) {
        return Error.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Invalid Url")
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handleIllegalArgumentException(IllegalArgumentException ex) {
        return Error.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Illegal Arguments passed. Argument can't be null or empty.")
                .build();
    }

    @ExceptionHandler(SearchEngineException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handleSearchEngineException(SearchEngineException ex) {
        return Error.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Some error occurred. Please try again later.")
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error handleRuntimeException(RuntimeException ex) {
        return Error.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Some error occurred. Please try again later.")
                .build();
    }
}
