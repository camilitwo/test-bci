package cl.bci.test.service;

import cl.bci.test.dto.UserRequestDTO;
import cl.bci.test.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserResponseDTO> saveUser(UserRequestDTO userRequestDTO);

    List<UserResponseDTO> getUsers();
}
