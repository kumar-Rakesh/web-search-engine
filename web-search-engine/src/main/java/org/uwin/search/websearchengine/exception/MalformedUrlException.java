package org.uwin.search.websearchengine.exception;

import lombok.NoArgsConstructor;
import org.uwin.search.websearchengine.exception.base.SearchEngineException;

@NoArgsConstructor
public class MalformedUrlException extends SearchEngineException {

    public MalformedUrlException(String message) {
        super(message);
    }
}
