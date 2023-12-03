package cl.bci.test.controller;

import cl.bci.test.configuration.JWTConfig;
import cl.bci.test.dto.DataAccessDTO;
import cl.bci.test.dto.JwtResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    @InjectMocks
    private LoginController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTConfig jwtConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void login_ValidCredentials_ReturnsJwtResponse() {

        DataAccessDTO userRequestDTO = new DataAccessDTO("user", "pass");


        when(jwtConfig.createToken(Mockito.anyString(), Mockito.anyString())).thenReturn("token");
        when(jwtConfig.getExpiration()).thenReturn(new Date());
        when(jwtConfig.getType()).thenReturn("Bearer");


        ResponseEntity<JwtResponseDTO> responseEntity = authController.login(userRequestDTO);


        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming HttpStatus.OK
        assertNotNull(responseEntity.getBody());
        assertEquals("token", responseEntity.getBody().getToken());
    }
}
