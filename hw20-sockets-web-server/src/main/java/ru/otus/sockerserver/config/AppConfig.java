package ru.otus.sockerserver.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class AppConfig {
    @JsonProperty("server")
    private ServerConfig serverConfig;
    @JsonProperty("uri-mapping")
    private Set<UriMapping> uriMapping;

    public AppConfig() {
    }

    public AppConfig(ServerConfig serverConfig, Set<UriMapping> uriMappings) {
        this.serverConfig = serverConfig;
        this.uriMapping = uriMappings;
    }

    public ServerConfig getServer() {
        return serverConfig;
    }

    public void setServer(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public Set<UriMapping> getUriMapping() {
        return uriMapping;
    }

    public void setUriMapping(Set<UriMapping> uriMapping) {
        this.uriMapping = uriMapping;
    }
}
