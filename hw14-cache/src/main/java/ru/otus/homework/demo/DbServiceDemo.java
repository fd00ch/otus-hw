package ru.otus.homework.demo;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.homework.core.repository.executor.DbExecutorImpl;
import ru.otus.homework.core.sessionmanager.TransactionRunnerJdbc;
import ru.otus.homework.crm.datasource.DriverManagerDataSource;
import ru.otus.homework.crm.model.Client;
import ru.otus.homework.crm.repository.ClientDataTemplateJdbc;
import ru.otus.homework.crm.service.DbServiceClientCachedImpl;
import ru.otus.homework.crm.service.DbServiceClientImpl;


import javax.sql.DataSource;

public class DbServiceDemo {
    private static final String URL = "jdbc:postgresql://localhost:5430/demoDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";

    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        var transactionRunner = new TransactionRunnerJdbc(dataSource);
        var dbExecutor = new DbExecutorImpl();
///
        var clientTemplate = new ClientDataTemplateJdbc(dbExecutor); //реализация DataTemplate, заточена на Client

///
        var dbServiceClient = new DbServiceClientImpl(transactionRunner, clientTemplate);
        var dbServiceClientCached = new DbServiceClientCachedImpl(transactionRunner, clientTemplate);
        Client savedFirstClient = dbServiceClient.saveClient(new Client("dbServiceFirst"));

        String firstClientId = savedFirstClient.getId(); // key for WeakHashMap
        String firstClientIdDuplicated = new String(savedFirstClient.getId());

        // dbService with cache
        // first call - get from DB
        var clientFirstCachedFirstCall = dbServiceClientCached.getClient(firstClientId);
        if (clientFirstCachedFirstCall.isEmpty()) {
            throw new RuntimeException("Client not found, id:" + firstClientId);
        }

        // second call - get from cache
        var clientFirstCachedSecondCall = dbServiceClientCached.getClient(firstClientId);
        if (clientFirstCachedSecondCall.isEmpty()) {
            throw new RuntimeException("Client not found, id:" + firstClientId);
        }

        savedFirstClient = null; // remove strong reference for key
        firstClientId = null; // remove strong reference for key
        System.gc(); // clear WeakHashMap for keys without strong references

        // third call - get from DB, not cache
        var clientFirstCachedThirdCall = dbServiceClientCached.getClient(firstClientIdDuplicated);
        if (clientFirstCachedThirdCall.isEmpty()) {
            throw new RuntimeException("Client not found, id:" + firstClientIdDuplicated);
        }
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
