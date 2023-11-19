package ru.otus.crm.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.otus.base.AbstractIntegrationTest;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.ClientDetails;
import ru.otus.crm.model.Manager;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class ClientRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ManagerRepository managerRepository;

    private static final String MANAGER_NAME = "m:" + System.currentTimeMillis();
    private static final String CLIENT_NAME = "client1Name";

    @BeforeEach
    void beforeAll() {
        clientRepository.deleteAll();
        managerRepository.deleteAll();
        var manager = new Manager(MANAGER_NAME, "ManagerFirst", new HashSet<>(),
                new ArrayList<>(), true);
        managerRepository.save(manager);

        var client = new Client(CLIENT_NAME, manager.getId(), 1, new ClientDetails("init1"));
        clientRepository.save(client);
    }

    @Test
    void whenSaveClient_thenReturnSavedClient() {
        // given
        Client client2 = new Client("client2Name", MANAGER_NAME, 1, new ClientDetails("init1"));

        // when
        var savedClient2 = clientRepository.save(client2);
        var foundSavedClient2 = clientRepository.findById(savedClient2.getId())
                .orElseThrow();

        // then
        assertEquals("client2Name", foundSavedClient2.getName());
        assertEquals(MANAGER_NAME, foundSavedClient2.getManagerId());
        assertEquals(1, foundSavedClient2.getOrderColumn());
        assertEquals("init1", foundSavedClient2.getClientInfo().getInfo());
    }

    @Test
    void whenFindAll_thenReturnAllClients() {
        // given
        Client client2 = new Client("client2Name", MANAGER_NAME, 1, new ClientDetails("init1"));

        // when
        clientRepository.save(client2);
        var allClients = clientRepository.findAll();

        // then
        assertEquals(2, allClients.size());
    }

    @Test
    void whenFindByName_thenReturnClient() {
        // when
        var foundClient = clientRepository.findByName(CLIENT_NAME)
                .orElseThrow();

        // then
        assertEquals(CLIENT_NAME, foundClient.getName());
    }

    @Test
    void whenFindByNameIgnoreCase_thenReturnClient() {
        // when
        var foundClient = clientRepository.findByNameIgnoreCase(CLIENT_NAME.toLowerCase())
                .orElseThrow();

        // then
        assertEquals(CLIENT_NAME, foundClient.getName());
    }

    @Test
    void whenUpdateClientName_thenReturnUpdatedClient() {
        // given
        var newClientName = "newName";
        var client = clientRepository.findByName(CLIENT_NAME).orElseThrow();
        var clientToUpdate = new Client(client.getId(), newClientName, client.getManagerId(), client.getOrderColumn(),
                client.getClientInfo());
        // when
        clientRepository.save(clientToUpdate);
        var updatedClient = clientRepository.findById(client.getId())
                .orElseThrow();

        // then
        assertEquals(newClientName, updatedClient.getName());
    }
}