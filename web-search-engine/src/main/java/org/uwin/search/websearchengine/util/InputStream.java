package org.uwin.search.websearchengine.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.uwin.search.websearchengine.exception.base.SearchEngineException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;

@Slf4j
@Service
public class InputStream {

    public String[] read(File file) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while (Objects.nonNull(line = reader.readLine())) {
                sb.append(line);
            }
            return split(sb.toString());
        } catch (IOException ex) {
            throw new SearchEngineException();
        } finally {
            if (Objects.nonNull(reader)) {
                reader.close();
            }
        }
    }

    private String[] split(String str) {
        str = str.replaceAll(RegexPattern.SPECIAL_CHAR, " ");
        StringTokenizer tokenizer = new StringTokenizer(str);
        String[] tokens = new String[tokenizer.countTokens()];
        int i = 0;
        while (tokenizer.hasMoreTokens()) {
            tokens[i++] = tokenizer.nextToken().toLowerCase();
        }
        return tokens;
    }
}
