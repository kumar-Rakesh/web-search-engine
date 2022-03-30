package org.uwin.search.websearchengine.util;




import java.util.Comparator;

import org.uwin.search.websearchengine.model.Page;


import org.uwin.search.websearchengine.model.Page;

import java.util.Comparator;


public class PageComparator implements Comparator<Page> {

    public int compare(Page p1, Page p2) {
        return p1.getOccurrences() < p2.getOccurrences() ? -1 : 1;
    }
}
