package ru.otus.sockerserver.server;

import ru.otus.sockerserver.config.AppConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final AppConfig appConfig;

    public Server(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void runServer() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(appConfig.getServer().getMaxConnections());
        try (ServerSocket serverSocket = new ServerSocket(appConfig.getServer().getPort())) {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("waiting for client connection");
                Socket clientSocket = serverSocket.accept();
                executorService.execute(new ClientSocketHandler(clientSocket, appConfig));
            }
        }
    }
}
