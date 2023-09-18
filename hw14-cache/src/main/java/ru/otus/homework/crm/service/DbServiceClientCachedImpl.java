package ru.otus.homework.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.homework.core.repository.DataTemplate;
import ru.otus.homework.core.sessionmanager.TransactionRunner;
import ru.otus.homework.crm.model.Client;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class DbServiceClientCachedImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientCachedImpl.class);

    private final DataTemplate<Client> dataTemplate;
    private final TransactionRunner transactionRunner;
    private final HwCache<String, Client> clientCache = new MyCache<>();

    public DbServiceClientCachedImpl(TransactionRunner transactionRunner, DataTemplate<Client> dataTemplate) {
        this.transactionRunner = transactionRunner;
        this.dataTemplate = dataTemplate;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionRunner.doInTransaction(connection -> {
            if (client.getId() == null) {
                var clientId = dataTemplate.insert(connection, client);
                var createdClient = new Client(clientId, client.getName());
                log.info("created client: {}", createdClient);
                clientCache.put(createdClient.getId(), createdClient); // put new item to the cache
                return createdClient;
            }
            dataTemplate.update(connection, client);
            log.info("updated client: {}", client);
            clientCache.put(client.getId(), client); // update existing item in the cache
            return client;
        });
    }

    @Override
    public Optional<Client> getClient(String id) {
        var getFromCacheStartTime = Instant.now();
        var clientFromCache = clientCache.get(id); // get from cache
        if (clientFromCache != null) {
            var getFromCacheEndTime = Instant.now();
            var delta  = Duration.between(getFromCacheStartTime, getFromCacheEndTime);
            log.info("client from cache: {}, time spend: {} ns", clientFromCache, delta.getNano());
            return Optional.of(clientFromCache);
        }
        return transactionRunner.doInTransaction(connection -> {
            var getFromDBStartTime = Instant.now();
            var clientOptional = dataTemplate.findById(connection, id);
            var getFromDBEndTime = Instant.now();
            var delta  = Duration.between(getFromDBStartTime, getFromDBEndTime);
            clientOptional.ifPresent(client -> { // put new item to the cache
                log.info("client from DB: {}, time spend: {} ns", client, delta.getNano());
                clientCache.put(id, client);
            });
            return clientOptional;
        });
    }

    @Override
    public List<Client> findAll() {
        return transactionRunner.doInTransaction(connection -> {
            var clientList = dataTemplate.findAll(connection);
            log.info("clientList:{}", clientList);
            clientList.forEach(client -> clientCache.put(client.getId(), client)); // put all items to the cache
            return clientList;
       });
    }
}
