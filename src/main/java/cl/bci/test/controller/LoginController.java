package cl.bci.test.controller;

import cl.bci.test.configuration.JWTConfig;
import cl.bci.test.dto.DataAccessDTO;
import cl.bci.test.dto.JwtResponseDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Api(tags = "Login")
public class LoginController {


    private final JWTConfig jwtConfig;


    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody DataAccessDTO userRequestDTO) {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(), userRequestDTO.getPassword())
        );
        return ResponseEntity.ok(JwtResponseDTO.builder()
                .token(jwtConfig.createToken(userRequestDTO.getUsername(), userRequestDTO.getPassword()))
                .expiration(jwtConfig.getExpiration())
                .type(jwtConfig.getType())
                .build());
    }
}
