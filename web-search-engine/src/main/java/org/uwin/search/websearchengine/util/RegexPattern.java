package org.uwin.search.websearchengine.util;

public class RegexPattern {

    public static final String URL = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)";
    public static final String SPECIAL_CHAR = "[^a-zA-Z0-9]";
}
