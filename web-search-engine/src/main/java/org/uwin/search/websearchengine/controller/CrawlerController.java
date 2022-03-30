package org.uwin.search.websearchengine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uwin.search.websearchengine.service.CrawlService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlService crawlService;

    @GetMapping("/crawl")
    public List<String> crawl(@RequestParam("url") String url) throws IOException {
        return crawlService.crawl(url);
    }
}
