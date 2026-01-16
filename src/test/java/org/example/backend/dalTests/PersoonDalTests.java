package org.example.backend.dalTests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.backend.TestConfig;
import org.example.backend.data.repository.CreateAccountRepo;
import org.example.backend.data.table.AccountTable;
import org.example.backend.domain.CreateAccountModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
@Transactional
class CreateAccountRepoTests {


    @Autowired
    private CreateAccountRepo repo;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("createAccount slaat een account op en hashed het wachtwoord")
    void testCreateAccount_persistsAndHashesPassword() {
        CreateAccountModel model = new CreateAccountModel(
                "john_doe",
                30,
                "john@example.com",   // email
                "Banaan12"            // password
        );

        CreateAccountModel result = repo.createAccount(model);

        assertNotNull(result);
        assertNotEquals("Banaan12", result.getPassword());

        TypedQuery<AccountTable> query = em.createQuery(
                "SELECT a FROM AccountTable a WHERE a.username = :username",
                AccountTable.class
        );
        query.setParameter("username", "john_doe");

        AccountTable table = query.getSingleResult();
        assertNotNull(table);
        assertEquals("john_doe", table.getUsername());
        assertEquals("john@example.com", table.getEmail());
        assertEquals(30, table.getAge());
        assertEquals(result.getPassword(), table.getPassword());
    }

}
