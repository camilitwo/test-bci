package cl.bci.test.controller;

import cl.bci.test.dto.UserRequestDTO;
import cl.bci.test.dto.UserResponseDTO;
import cl.bci.test.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.MockitoAnnotations;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser_ValidRequest_ReturnsResponseEntity() {
        UserRequestDTO userRequestDTO = new UserRequestDTO(/* set valid properties */);
        String authorizationHeader = "validAuthorizationHeader";

        when(userService.saveUser(Mockito.any(UserRequestDTO.class), Mockito.anyString()))
                .thenReturn(new ResponseEntity<>(new UserResponseDTO(), HttpStatus.OK));
        ResponseEntity<UserResponseDTO> responseEntity = userController.saveUser(userRequestDTO, authorizationHeader);


        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void saveUser_InvalidRequest_ReturnsBadRequest() {

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        String authorizationHeader = "validAuthorizationHeader";
        when(userService.saveUser(Mockito.any(UserRequestDTO.class), Mockito.anyString()))
                .thenReturn(new ResponseEntity<>(new UserResponseDTO(), HttpStatus.BAD_REQUEST));

        ResponseEntity<UserResponseDTO> responseEntity = userController.saveUser(userRequestDTO, authorizationHeader);


        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void saveUser_ServiceReturnsError_ReturnsInternalServerError() {
        when(userService.saveUser(Mockito.any(UserRequestDTO.class), Mockito.anyString()))
                .thenThrow(new RuntimeException("Simulated service error"));


        doThrow(new RuntimeException("Simulated service error"))
                .when(userService).saveUser(Mockito.any(UserRequestDTO.class), Mockito.anyString());

    }
}
