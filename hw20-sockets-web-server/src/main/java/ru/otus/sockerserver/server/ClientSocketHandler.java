package ru.otus.sockerserver.server;

import ru.otus.sockerserver.config.AppConfig;
import ru.otus.sockerserver.config.UriMapping;
import ru.otus.sockerserver.exception.PageNotFoundException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientSocketHandler implements Runnable {
    private static final String HTTP_PROTOCOL = "HTTP/1.0";
    private static final String PAGE_FOLDER = "static";
    private static final String INDEX_PAGE = "index.html";
    private static final String NOT_FOUND_PAGE = "not-found.html";
    private static final String SERVER_ERROR_PAGE = "server-error.html";
    Socket clientSocket;
    AppConfig appConfig;

    public ClientSocketHandler(Socket clientSocket, AppConfig appConfig) {
        this.clientSocket = clientSocket;
        this.appConfig = appConfig;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String input = getInput(in);
            String targetPage = getTargetPage(input);
            var targetPageFile = appConfig.getUriMapping().stream()
                    .filter(uriMapping -> uriMapping.getUri().equals(targetPage))
                    .findFirst()
                    .map(UriMapping::getHtmlFilePath)
                    .orElseThrow(() -> new PageNotFoundException("Page is not found: %s".formatted(targetPage)));
            var targetPageFileStream = getPageFileFromResourceAsStream(targetPageFile);
            generateOut(targetPageFileStream, HttpURLConnection.HTTP_OK);
        } catch (PageNotFoundException ex) {
            var pageNotFoundFileStream =
                    getPageFileFromResourceAsStream("%s/%s".formatted(PAGE_FOLDER, NOT_FOUND_PAGE));
            generateOut(pageNotFoundFileStream, HttpURLConnection.HTTP_NOT_FOUND);
        } catch (Exception ex) {
            var pageServerErrorFileStream =
                    getPageFileFromResourceAsStream("%s/%s".formatted(PAGE_FOLDER, SERVER_ERROR_PAGE));
            generateOut(pageServerErrorFileStream, HttpURLConnection.HTTP_INTERNAL_ERROR);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getInput(BufferedReader in) throws IOException {
        StringBuilder totalInput = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            totalInput.append(line);
        }
        return totalInput.toString();
    }

    private static String getTargetPage(String request) {
        String[] requestLines = request.split("\n");
        String[] requestLine = requestLines[0].split(" ");
        var result = requestLine[1];
        if (result.equals("/")) {
            result = "/%s".formatted(INDEX_PAGE);
        }
        return result;
    }

    private InputStream getPageFileFromResourceAsStream(String fileName) throws PageNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new PageNotFoundException("Page is not found: " + fileName);
        } else {
            return inputStream;
        }
    }
    private void generateOut(InputStream targetPageFileStream, int responseCode) {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.write("%s %d\r\n".formatted(HTTP_PROTOCOL, responseCode));
            out.write("\r\n");
            String line;
            out.write(new String(targetPageFileStream.readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
