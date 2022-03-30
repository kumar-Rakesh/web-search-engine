package org.uwin.search.websearchengine.exception.base;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SearchEngineException extends RuntimeException {

    public SearchEngineException(String message) {
        super(message);
    }
}
