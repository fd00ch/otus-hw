package ru.otus.sockerserver;

import ru.otus.sockerserver.config.AppConfig;
import ru.otus.sockerserver.server.Server;
import ru.otus.sockerserver.utils.AppConfigLoader;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        AppConfigLoader appConfigLoader = new AppConfigLoader();
        AppConfig appConfig = appConfigLoader.load();
        new Server(appConfig).runServer();
    }
}
