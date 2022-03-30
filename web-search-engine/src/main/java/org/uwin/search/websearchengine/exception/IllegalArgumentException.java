package org.uwin.search.websearchengine.exception;

import org.uwin.search.websearchengine.exception.base.SearchEngineException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IllegalArgumentException extends SearchEngineException {

    public IllegalArgumentException(String message) {
        super(message);
    }

}
