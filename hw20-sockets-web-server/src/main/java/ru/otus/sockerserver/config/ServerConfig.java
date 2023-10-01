package ru.otus.sockerserver.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerConfig {
    @JsonProperty("port")
    private int port;
    @JsonProperty("max-connections")
    private int maxConnections;

    public ServerConfig() {
    }

    public ServerConfig(int port, int maxConnections) {
        this.port = port;
        this.maxConnections = maxConnections;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }
}
