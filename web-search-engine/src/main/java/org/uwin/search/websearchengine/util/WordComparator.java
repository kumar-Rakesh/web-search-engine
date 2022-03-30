package org.uwin.search.websearchengine.util;


import java.util.Comparator;

import org.uwin.search.websearchengine.model.Word;

public class WordComparator implements Comparator<Word> {

    public int compare(Word w1, Word w2) {
        return w1.getVal() < w2.getVal() ? -1 : 1;
    }
}
