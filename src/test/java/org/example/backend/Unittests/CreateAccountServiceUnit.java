package org.example.backend.Unittests;

import org.example.backend.application.port.ICreateAccountRepo;
import org.example.backend.application.service.CreateAccountService;
import org.example.backend.domain.CreateAccountModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CreateAccountServiceUnit {

    private CreateAccountService subject;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ICreateAccountRepo iCreateAccountRepo;

    @BeforeEach
    void setUp() {
        subject = new CreateAccountService(passwordEncoder, iCreateAccountRepo);
    }

    @Test
    void shouldCreateAccountWhenInputIsValid() {
        // Arrange
        CreateAccountModel model = new CreateAccountModel(
                "Jeroen",
                21,
                "Test@gmail.com",
                "Password123"
        );

        when(passwordEncoder.encode("Password123"))
                .thenReturn("ENCODED_PASSWORD");

        CreateAccountModel savedModel = new CreateAccountModel(
                "Jeroen",
                21,
                "Test@gmail.com",
                "ENCODED_PASSWORD"
        );

        when(iCreateAccountRepo.createAccount(any(CreateAccountModel.class)))
                .thenReturn(savedModel);

        // Act
        CreateAccountModel result = subject.createAccount(model);

        // Assert
        assertThat(result.getUsername(), is("Jeroen"));
        assertThat(result.getAge(), is(21));
        assertThat(result.getEmail(), is("Test@gmail.com"));
        assertThat(result.getPassword(), is("ENCODED_PASSWORD"));

        verify(passwordEncoder).encode("Password123");
        verify(iCreateAccountRepo).createAccount(any(CreateAccountModel.class));
    }

    @Test
    void shouldThrowWhenUsernameIsEmpty() {
        CreateAccountModel model = new CreateAccountModel(
                "",
                21,
                "Test@gmail.com",
                "Password123"
        );

        assertThrows(IllegalArgumentException.class, () -> subject.createAccount(model));
    }

    @Test
    void shouldThrowWhenAgeIsInvalid() {
        CreateAccountModel model = new CreateAccountModel(
                "Wink",
                0,
                "wink@example.com",
                "password123"
        );

        assertThrows(IllegalArgumentException.class, () -> subject.createAccount(model));
    }

    @Test
    void shouldThrowWhenPasswordIsEmpty() {
        CreateAccountModel model = new CreateAccountModel(
                "Wink",
                21,
                "wink@example.com",
                ""
        );

        assertThrows(IllegalArgumentException.class, () -> subject.createAccount(model));
    }

    @Test
    void shouldThrowWhenEmailIsInvalid() {
        CreateAccountModel model = new CreateAccountModel(
                "Wink",
                21,
                "invalidEmail",
                "password123"
        );

        assertThrows(IllegalArgumentException.class, () -> subject.createAccount(model));
    }
}
