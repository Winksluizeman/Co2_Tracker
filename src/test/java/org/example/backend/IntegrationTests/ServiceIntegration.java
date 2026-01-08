package org.example.backend.IntegrationTests;

import org.example.backend.Application.Port.ICreateAccountRepo;
import org.example.backend.Application.service.CreateAccountService;
import org.example.backend.Domain.CreateAccountModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAccountServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ICreateAccountRepo repo;

    private CreateAccountService service;

    @BeforeEach
    void setUp() {
        service = new CreateAccountService(passwordEncoder, repo);
    }

    @Test
    void shouldEncodePasswordAndSaveModel() {
        // Arrange
        CreateAccountModel model = new CreateAccountModel(
                "Jeroen", 21, "Test@Gmail.com", "Password123"
        );

        when(passwordEncoder.encode("Password123")).thenReturn("ENCODED");
        when(repo.createAccount(any())).thenAnswer(inv -> inv.getArgument(0));

        // Act
        CreateAccountModel result = service.createAccount(model);

        // Assert
        assertEquals("ENCODED", result.getPassword());

        ArgumentCaptor<CreateAccountModel> captor = ArgumentCaptor.forClass(CreateAccountModel.class);
        verify(repo).createAccount(captor.capture());

        assertEquals("Jeroen", captor.getValue().getUsername());
        assertEquals("ENCODED", captor.getValue().getPassword());
    }

    @Test
    void shouldThrowWhenUsernameIsEmpty() {
        CreateAccountModel model = new CreateAccountModel("", 21, "Test@Gmail.com", "Password123");
        assertThrows(IllegalArgumentException.class, () -> service.createAccount(model));
    }
}
