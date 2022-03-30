package org.uwin.search.websearchengine.model;

import java.util.Map;


public interface Trie<V> extends Tree {

    V get(String key);

    V put(String key, V value);

    V put(String key, V value, Page page);

    boolean containsPage(String key, String page);

    boolean removePage(String key, String page);

    Page getPage(String key, String page);

    Map<Page, Page> getAllPages(String key);
    
    Map<Word, Word> autoComplete(String key);
}
