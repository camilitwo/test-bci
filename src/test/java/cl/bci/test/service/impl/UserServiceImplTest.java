package cl.bci.test.service.impl;

import cl.bci.test.dto.PhoneDTO;
import cl.bci.test.dto.UserRequestDTO;
import cl.bci.test.dto.UserResponseDTO;
import cl.bci.test.model.Phone;
import cl.bci.test.model.User;
import cl.bci.test.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser_ValidRequest_ReturnsCreatedResponse() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO(/* set valid properties */);
        String authorizationHeader = "validAuthorizationHeader";

        // Mocking the behavior of userRepository
        when(userRepository.save(any(User.class))).thenReturn(createMockUser());

        // Act
        ResponseEntity<UserResponseDTO> responseEntity = userService.saveUser(
                UserRequestDTO.builder()
                        .email("sasa@sasda.cl")
                        .name("sasa")
                        .password("sasa")
                        .phones(Collections.singletonList(PhoneDTO.builder()
                                .citycode("1")
                                .contrycode("1")
                                .number("123456789")
                                .build()))
                .build(), authorizationHeader);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        // Add more assertions based on the expected behavior
    }

    // Add more test cases for error scenarios, edge cases, etc.

    private User createMockUser() {
        User user = new User();
        user.setId("1L");
        user.setName("mockUser");
        Phone phone = new Phone();
        phone.setCitycode("1");
        phone.setContrycode("1");
        phone.setNumber("123456789");
        user.setPhones(Collections.singletonList(phone));
        // Set other properties as needed for a valid user
        return user;
    }
}
