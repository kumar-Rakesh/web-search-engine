package org.uwin.search.websearchengine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Word {

    private String key;
    private long val;

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Word)) {
            return false;
        }
        Word word = (Word) o;
        return key.equalsIgnoreCase(word.getKey());
    }

}
