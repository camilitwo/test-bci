package cl.bci.test.controller;

import cl.bci.test.dto.ProblemResponseDTO;
import cl.bci.test.dto.UserRequestDTO;
import cl.bci.test.dto.UserResponseDTO;
import cl.bci.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
@Api(tags = "User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value = "Endpoint para almacenar usuarios en base de datos", notes = "Save user", tags = {
            "User" })
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Created", response = UserResponseDTO.class),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = ProblemResponseDTO.class),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad Request", response = ProblemResponseDTO.class),
            @io.swagger.annotations.ApiResponse(code = 503, message = "Service Failure", response = ProblemResponseDTO.class) })
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO,
        @RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader) {

        return userService.saveUser(userRequestDTO, authorizationHeader);
    }


}
