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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class ManagerRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ManagerRepository managerRepository;

    private static final String MANAGER1_NAME = "m:" + "1699989591001";
    private static final String MANAGER2_NAME = "m:" + "1699989591002";
    private static final String MANAGER1_LABEL = "ManagerFirst";
    private static final String MANAGER2_LABEL = "ManagerSecond";
    private static final String CLIENT1_NAME = "managClient1";
    private static final String CLIENT2_NAME = "managClient2";
    private static final String CLIENT3_NAME = "managClient3";
    private static final String CLIENT4_NAME = "managClient4";


    @BeforeEach
    void beforeAll() {
        managerRepository.deleteAll();
        clientRepository.deleteAll();
        var manager1 = new Manager(MANAGER1_NAME, MANAGER1_LABEL,
                Set.of(new Client(CLIENT1_NAME, MANAGER1_NAME, 1, new ClientDetails("info1")),
                        new Client(CLIENT2_NAME, MANAGER1_NAME, 2, new ClientDetails("info2"))),
                new ArrayList<>(), true);
        managerRepository.save(manager1);
    }

    @Test
    void whenSaveManager_thenReturnSavedManager() {
        // given
        Set<Client> clients = Set.of(new Client(CLIENT3_NAME, MANAGER2_NAME, 1, new ClientDetails("info3")),
                new Client(CLIENT4_NAME, MANAGER2_NAME, 2, new ClientDetails("info4")));
        Manager manager2 = new Manager(MANAGER2_NAME, MANAGER2_LABEL, clients, new ArrayList<>(), true);

        // when
        managerRepository.save(manager2);
        var foundSavedManager2 = managerRepository.findById(MANAGER2_NAME)
                .orElseThrow();

        // then
        assertEquals(MANAGER2_NAME, foundSavedManager2.getId());
        assertEquals(2, foundSavedManager2.getClients().size());
        assertEquals(2, foundSavedManager2.getClientsOrdered().size());
        assertEquals(MANAGER2_LABEL, foundSavedManager2.getLabel());
    }

    @Test
    void whenFindAll_thenReturnAllManagers() {
        // given
        Manager manager2 = new Manager(MANAGER2_NAME, MANAGER2_LABEL, new HashSet<>(), new ArrayList<>(), true);

        // when
        managerRepository.save(manager2);
        var allManagers = managerRepository.findAll();

        // then
        assertEquals(2, allManagers.size());
    }

    @Test
    void whenFindById_thenReturnManager() {
        // when
        var foundManager = managerRepository.findById(MANAGER1_NAME)
                .orElseThrow();

        // then
        assertEquals(MANAGER1_NAME, foundManager.getId());
    }

    @Test
    void whenUpdateManagerLabel_thenReturnUpdatedManager() {
        // given
        var newManagerLabel = "ManagerFirstUpdated";
        var manager = managerRepository.findById(MANAGER1_NAME).orElseThrow();
        var managerToUpdate = new Manager(manager.getId(), newManagerLabel, new HashSet<>(), new ArrayList<>(), false);

        // when
        managerRepository.save(managerToUpdate);
        var updatedManager = managerRepository.findById(MANAGER1_NAME).orElseThrow();

        // then
        assertEquals(newManagerLabel, updatedManager.getLabel());
        assertEquals(0, updatedManager.getClients().size());
    }
}