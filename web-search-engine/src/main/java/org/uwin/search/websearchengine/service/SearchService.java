package org.uwin.search.websearchengine.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.uwin.search.websearchengine.model.Page;
import org.uwin.search.websearchengine.model.Trie;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final Trie<Long> wordMap;

    public List<Page> search(String key) {
        if (wordMap.contains(key)) {
            Map<Page, Page> pages = wordMap.getAllPages(key);
            return mapToList(pages);
        }
        return List.of();
    }

    private <K> List<K> mapToList(Map<K, K> map) {
        LinkedList<K> results = new LinkedList<>();
        map.forEach((k, v) -> results.addFirst(k));
        return results;
    }
}
