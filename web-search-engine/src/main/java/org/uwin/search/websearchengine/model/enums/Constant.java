package org.uwin.search.websearchengine.model.enums;

public enum Constant {

    HTML("html"),
    TEXT("text"),
    HTML_EXT(".html"),
    TXT_EXT(".txt");

    private final String value;

    private Constant(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
