package ru.otus.sockerserver.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UriMapping {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("path")
    private String htmlFilePath;

    public UriMapping() {
    }

    public UriMapping(String uri, String htmlFilePath) {
        this.uri = uri;
        this.htmlFilePath = htmlFilePath;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHtmlFilePath() {
        return htmlFilePath;
    }

    public void setHtmlFilePath(String htmlFilePath) {
        this.htmlFilePath = htmlFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UriMapping that = (UriMapping) o;

        if (!Objects.equals(uri, that.uri)) return false;
        return Objects.equals(htmlFilePath, that.htmlFilePath);
    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + (htmlFilePath != null ? htmlFilePath.hashCode() : 0);
        return result;
    }
}
