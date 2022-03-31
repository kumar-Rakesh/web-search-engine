package org.uwin.search.websearchengine.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.uwin.search.websearchengine.config.AppConfig;
import org.uwin.search.websearchengine.model.Page;
import org.uwin.search.websearchengine.model.Trie;
import org.uwin.search.websearchengine.model.Word;
import org.uwin.search.websearchengine.util.WordComparator;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final Trie<Long> wordMap;
    private final Set<String> dictionary;
    private final AppConfig config;

    public List<Page> search(String key) {
        if (wordMap.contains(key)) {
            Map<Page, Page> pages = wordMap.getAllPages(key);
            return mapToList(pages);
        }
        return List.of();
    }

    public List<Word> autoComplete(String key) {
        Map<Word, Word> possibleKeys = wordMap.autoComplete(key);
        LinkedList<Word> results = new LinkedList<>();
        possibleKeys.forEach((k, v) -> results.addFirst(k));
        return results;
    }

    private <K> List<K> mapToList(Map<K, K> map) {
        LinkedList<K> results = new LinkedList<>();
        map.forEach((k, v) -> results.addFirst(k));
        return results;
    }

    public List<Word> spellCheck(String key) {
        TreeMap<Word, Word> map = new TreeMap<>(new WordComparator());
        for (String token : dictionary) {
            int editDistance = getEditDistance(key, token);
            if (map.size() == config.getLimit()) {
                Word lastKey = map.lastKey();
                if (lastKey.getVal() > editDistance) {
                    map.remove(lastKey);
                    Word w = Word.builder().key(token).val(editDistance).build();
                    map.put(w, w);
                }
            } else {
                Word w = Word.builder().key(token).val(editDistance).build();
                map.put(w, w);
            }
        }
        List<Word> list = new LinkedList<>();
        map.forEach((k, v) -> list.add(k));
        return list;
    }

    private int getEditDistance(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int dp[][] = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; ++i) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; ++i) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int ins = 1 + dp[i - 1][j];
                    int del = 1 + dp[i][j - 1];
                    int rep = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(Math.min(ins, del), rep);
                }
            }
        }
        return dp[len1][len2];
    }
}
