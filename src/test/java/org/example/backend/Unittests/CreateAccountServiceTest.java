package org.example.backend.Unittests;

import org.example.backend.Application.Port.ICreateAccountRepo;
import org.example.backend.Application.service.CreateAccountService;
import org.example.backend.Api.dto.CreateAccountDto;
import org.example.backend.Domain.CreateAccountModel;
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
class CreateAccountServiceTest {

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
        CreateAccountDto dto = new CreateAccountDto();
        dto.setUsername("Jeroen");
        dto.setAge(21);
        dto.setPassword("Password123");
        dto.setEmail("Test@gmail.com");

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
        CreateAccountModel result = subject.createAccount(dto);

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
        CreateAccountDto dto = new CreateAccountDto();
        dto.setUsername("");
        dto.setAge(21);
        dto.setPassword("Password123");
        dto.setEmail("Test@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> subject.createAccount(dto));
    }

    @Test
    void shouldThrowWhenAgeIsInvalid() {
        CreateAccountDto dto = new CreateAccountDto();
        dto.setUsername("Wink");
        dto.setAge(0);
        dto.setPassword("password123");
        dto.setEmail("wink@example.com");

        assertThrows(IllegalArgumentException.class, () -> subject.createAccount(dto));
    }

    @Test
    void shouldThrowWhenPasswordIsEmpty() {
        CreateAccountDto dto = new CreateAccountDto();
        dto.setUsername("Wink");
        dto.setAge(21);
        dto.setPassword("");
        dto.setEmail("wink@example.com");

        assertThrows(IllegalArgumentException.class, () -> subject.createAccount(dto));
    }

    @Test
    void shouldThrowWhenEmailIsInvalid() {
        CreateAccountDto dto = new CreateAccountDto();
        dto.setUsername("Wink");
        dto.setAge(21);
        dto.setPassword("password123");
        dto.setEmail("invalidEmail");

        assertThrows(IllegalArgumentException.class, () -> subject.createAccount(dto));
    }
}
