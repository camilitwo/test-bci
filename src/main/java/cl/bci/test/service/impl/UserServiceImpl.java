package cl.bci.test.service.impl;

import Util.PasswordEncryption;
import cl.bci.test.dto.UserRequestDTO;
import cl.bci.test.dto.UserResponseDTO;
import cl.bci.test.exception.ProblemException;
import cl.bci.test.model.Phone;
import cl.bci.test.model.User;
import cl.bci.test.repository.UserRepository;
import cl.bci.test.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public ResponseEntity<UserResponseDTO> saveUser(UserRequestDTO userRequestDTO, String authorizationHeader) {
        CompletableFuture<UserResponseDTO> userResponseDTOCompletableFuture = CompletableFuture
                .supplyAsync(() -> buildUserFromRequest(userRequestDTO, authorizationHeader))
                .thenApplyAsync(user -> {
                    userExist(user);
                    user.setPassword(PasswordEncryption.encrypt(user.getPassword()));
                    User savedUser = userRepository.save(user);
                    UserResponseDTO responseDTO = new UserResponseDTO();
                    BeanUtils.copyProperties(savedUser, responseDTO);
                    return responseDTO;
                })
                .exceptionally(throwable -> {
                    throw new ProblemException(throwable.getMessage());
                });

        return new ResponseEntity<>(userResponseDTOCompletableFuture.join(), HttpStatus.CREATED);
    }


    private void userExist(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ProblemException("El correo ya está registrado");
        }
    }

    private User buildUserFromRequest(UserRequestDTO userRequestDTO, String authorizationHeader) {
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);

        List<Phone> phones = userRequestDTO.getPhones().stream()
                .map(phoneDTO -> {
                    Phone phone = new Phone();
                    BeanUtils.copyProperties(phoneDTO, phone);
                    return phone;
                })
                .collect(Collectors.toList());

        user.setPhones(phones);
        user.setCreated(new Date());
        user.setLastLogin(new Date());
        user.setIsActive(true);
        user.setToken(authorizationHeader.substring(7));
        return user;
    }
}
