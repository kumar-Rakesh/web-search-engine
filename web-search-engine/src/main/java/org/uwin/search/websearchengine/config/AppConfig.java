package org.uwin.search.websearchengine.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.uwin.search.websearchengine.model.Trie;
import org.uwin.search.websearchengine.model.impl.TernarySearchTree;

import java.util.HashSet;
import java.util.Set;

@Data
@Configuration
public class AppConfig {

    @Value("${file-path}")
    private String path;

    @Value("${size}")
    private int limit;

    @Bean
    public Trie<Long> getTernarySearchTree() {
        return new TernarySearchTree<Long>();
    }

    @Bean
    public Set<String> getDictionary() {
        return new HashSet<>();
    }
}
