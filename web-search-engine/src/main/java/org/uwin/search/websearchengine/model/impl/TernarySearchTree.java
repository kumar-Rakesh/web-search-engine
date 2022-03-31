package org.uwin.search.websearchengine.model.impl;

import org.uwin.search.websearchengine.model.Page;
import org.uwin.search.websearchengine.model.Trie;

import org.uwin.search.websearchengine.model.Word;
import org.uwin.search.websearchengine.util.PageComparator;
import org.uwin.search.websearchengine.util.WordComparator;
import org.uwin.search.websearchengine.exception.IllegalArgumentException;

import org.uwin.search.websearchengine.util.PageComparator;


import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class TernarySearchTree<V> implements Trie<V> {

    private int size;
    private Node root;
    private final int limit = 20;

    private class Node {
        private char c;
        private Node left, mid, right;
        private V val;
        private Map<Page, Page> pages;
        private boolean isWord;
    }

    public int size() {
        return this.size;
    }

    public Boolean contains(String key) {
        return Objects.nonNull(get(this.root, key, 0));
    }

    public V get(String key) {
        Node node = get(this.root, key, 0);
        return Objects.isNull(node) ? null : node.val;
    }

    private Node get(Node root, String key, int index) {
        if (Objects.isNull(root)) {
            return null;
        }
        if (Objects.isNull(key) || key.isEmpty()) {
            throw new IllegalArgumentException("Key can't be null or empty!");
        }
        char expectedChar = key.charAt(index);
        if (expectedChar < root.c) {
            return get(root.left, key, index);
        } else if (expectedChar > root.c) {
            return get(root.right, key, index);
        } else if (index < key.length() - 1) {
            return get(root.mid, key, index + 1);
        } else {
            return root.isWord ? root : null;
        }
    }

    public V put(String key, V value) {
        if (Objects.isNull(key) || key.isEmpty()) {
            throw new IllegalArgumentException("Key can't be null or empty!");
        }
        return put(key, value, null);
    }

    public V put(String key, V value, Page page) {
        if (Objects.isNull(get(this.root, key, 0))) {
            ++this.size;
        }
        return (this.root = put(this.root, key, value, page, 0)).val;
    }

    private Node put(Node root, String key, V value, Page page, int index) {
        char c = key.charAt(index);
        if (Objects.isNull(root)) {
            root = new Node();
            root.c = c;
            root.isWord = false;
        }
        if (c < root.c) {
            root.left = put(root.left, key, value, page, index);
        } else if (c > root.c) {
            root.right = put(root.right, key, value, page, index);
        } else if (index < key.length() - 1) {
            root.mid = put(root.mid, key, value, page, index + 1);
        } else {
            root.val = value;
            if (Objects.nonNull(page)) {
                if (Objects.isNull(root.pages)) {
                    root.pages = new TreeMap(new PageComparator());
                }
                root.pages.put(page, page);
            }
            root.isWord = true;
        }
        return root;
    }

    public Map<Page, Page> getAllPages(String key) {
        return getAllPages(this.root, key);
    }

    private Map<Page, Page> getAllPages(Node root, String key) {
        Node node = get(root, key, 0);
        if (Objects.nonNull(node)) {
            return node.pages;
        }
        return null;
    }

    public boolean containsPage(String key, String page) {
        if (Objects.isNull(key) || Objects.isNull(page) || key.isEmpty() || page.isEmpty()) {
            throw new IllegalArgumentException("Key or Page can't be null or empty!!");
        }
        Map<Page, Page> pages = getAllPages(this.root, key);
        if (Objects.isNull(pages)) {
            return false;
        }
        return pages.containsKey(Page.builder().page(page).build());
    }

    public Page getPage(String key, String page) {
        if (Objects.isNull(key) || Objects.isNull(page) || key.isEmpty() || page.isEmpty()) {
            throw new IllegalArgumentException("Key or Page can't be null or empty!!");
        }
        Map<Page, Page> pages = getAllPages(this.root, key);
        if (Objects.isNull(pages)) {
            return null;
        }
        return pages.get(Page.builder().page(page).build());
    }

    public boolean removePage(String key, String page) {
        if (Objects.isNull(key) || Objects.isNull(page) || key.isEmpty() || page.isEmpty()) {
            throw new IllegalArgumentException("Key or Page can't be null or empty!!");
        }
        Map<Page, Page> pages = getAllPages(this.root, key);
        if (Objects.isNull(pages)) {
            return false;
        }
        pages.remove(Page.builder().page(page).build());
        return true;
    }

    
    public Map<Word, Word> autoComplete(String key) {
        if (Objects.isNull(this.root)) {
            return null;
        }
        if (Objects.isNull(key) || key.isEmpty()) {
            throw new IllegalArgumentException("Key can't be null or empty!");
        }
        Node node = getRoot(this.root, key, 0);
        if (Objects.isNull(node)) {
            return Map.of();
        }
        Map<Word, Word> possibleKeys = new TreeMap(new WordComparator());
        autoComplete(node.left, key, possibleKeys);
        autoComplete(node.right, key, possibleKeys);
        autoComplete(node.mid, key, possibleKeys);
        return possibleKeys;
    }

    private Node getRoot(Node root, String key, int index) {
        if (Objects.isNull(root)) {
            return null;
        }
        char expectedChar = key.charAt(index);
        if (expectedChar < root.c) {
            return getRoot(root.left, key, index);
        } else if (expectedChar > root.c) {
            return getRoot(root.right, key, index);
        } else if (index < key.length() - 1) {
            return getRoot(root.mid, key, index + 1);
        } else {
            return root;
        }
    }

    private void autoComplete(Node root, String key, Map<Word, Word> keys) {
        if (Objects.isNull(root)) {
            return;
        }
        if (root.isWord) {
            Word word = Word.builder().key(key + root.c).val((long) root.val).build();
            if (keys.size() == limit) {
                TreeMap<Word, Word> map = (TreeMap<Word, Word>) keys;
                map.remove(map.firstKey());
            }
            keys.put(word, word);
        }
        autoComplete(root.left, key + root.c, keys);
        autoComplete(root.right, key + root.c, keys);
        autoComplete(root.mid, key + root.c, keys);
    }


}
