package ru.otus.publishersubscriber.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public abstract class EventListenerToLogFile implements EventListener {
    private static final String OUTPUT_FOLDER = "output";
    protected final String logFileName;

    protected EventListenerToLogFile(String logFileName) {
        this.logFileName = logFileName;
    }

    protected void appendLine(String line) throws IOException {
        File outputFolder = new File(OUTPUT_FOLDER);
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
        File logFile = new File(outputFolder, logFileName);
        var contentToAppend = logFile.length() == 0 ? line : System.lineSeparator() + line;
        Files.writeString(logFile.toPath(), contentToAppend, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
