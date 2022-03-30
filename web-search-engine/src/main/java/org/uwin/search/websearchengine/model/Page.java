package org.uwin.search.websearchengine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Page {

    private String page;
    private long occurrences;

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Page)) {
            return false;
        }
        Page p = (Page) o;
        return this.page.equalsIgnoreCase(p.page);
    }
}
