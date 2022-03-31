package org.uwin.search.websearchengine.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.uwin.search.websearchengine.exception.MalformedUrlException;
import org.uwin.search.websearchengine.service.CrawlService;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.uwin.search.websearchengine.util.RegexPattern.URL;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CrawlerController {

    private final CrawlService crawlService;

    @GetMapping("/crawl")
    public List<String> crawl(@RequestParam("url") String url) throws IOException {
        Pattern pattern = Pattern.compile(URL);
        Matcher matcher = pattern.matcher(url);
        if (!matcher.matches()) {
            throw new MalformedUrlException("URL: " + url + " is invalid!");
        }
        return crawlService.crawl(url);
    }
}
