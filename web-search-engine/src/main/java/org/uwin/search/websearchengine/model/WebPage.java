package org.uwin.search.websearchengine.model;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
@Builder
public class WebPage {

    private List<File> htmlFiles;
    private List<File> textFiles;

}
