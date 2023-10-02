package ru.otus.publishersubscriber.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.publishersubscriber.Event;
import java.io.IOException;

public class InsertListener extends EventListenerToLogFile {
    private static final String INSERT_LOG_FILE = "inserted.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger(InsertListener.class);

    public InsertListener() {
        super(INSERT_LOG_FILE);
    }

    @Override
    public void accept(Event event) {
        try {
            appendLine(event.object().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Inserted " + event.object());
    }
}
