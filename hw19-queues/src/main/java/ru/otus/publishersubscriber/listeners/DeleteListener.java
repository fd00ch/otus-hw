package ru.otus.publishersubscriber.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.publishersubscriber.Event;
import java.io.IOException;

public class DeleteListener extends EventListenerToLogFile {
    private static final String DELETE_LOG_FILE = "deleted.txt";
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteListener.class);

    public DeleteListener() {
        super(DELETE_LOG_FILE);
    }

    @Override
    public void accept(Event event) {
        try {
            appendLine(event.object().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Deleted " + event.object());
    }
}
