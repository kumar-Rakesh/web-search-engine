package org.uwin.search.websearchengine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uwin.search.websearchengine.model.Page;
import org.uwin.search.websearchengine.model.Word;
import org.uwin.search.websearchengine.service.SearchService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public List<Page> search(@RequestParam("query") String query) {
        log.info("Query->{}", query);
        return searchService.search(query.toLowerCase());
    }

    @GetMapping("/search/autoComplete")
    public List<Word> autoComplete(@RequestParam("query") String query) {
        return searchService.autoComplete(query);
    }

    @GetMapping("/search/like")
    public List<Word> like(@RequestParam("query") String query) {
        return searchService.spellCheck(query);
    }

}
