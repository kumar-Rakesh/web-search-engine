package org.uwin.search.websearchengine.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.uwin.search.websearchengine.util.RegexPattern.URL;


@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlService {

    public List<String> crawl(String url) throws IOException {
        List<String> urls = getUrls(url);
        return urls;
    }

    private List<String> getUrls(String url) throws IOException {
        List<String> urls = new ArrayList<>();
        Document document = Jsoup.connect(url).get();
        Elements links = document.select("a[href]");
        for (Element link : links) {
            String s = link.attr("abs:href");
            String regex = URL;
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(s);
            while (m.find()) {
                urls.add(m.group(0));
            }
        }
        return urls;
    }

}
