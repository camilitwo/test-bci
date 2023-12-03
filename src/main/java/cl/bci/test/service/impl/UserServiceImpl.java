package cl.bci.test.service.impl;

import cl.bci.test.dto.UserRequestDTO;
import cl.bci.test.dto.UserResponseDTO;
import cl.bci.test.model.Phone;
import cl.bci.test.model.User;
import cl.bci.test.repository.UserRepository;
import cl.bci.test.service.UserService;
import lombok.RequiredArgsConstructor;
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
                    User savedUser = userRepository.save(user);
                    UserResponseDTO responseDTO = new UserResponseDTO();
                    BeanUtils.copyProperties(savedUser, responseDTO);
                    return responseDTO;
                });

        return new ResponseEntity<>(userResponseDTOCompletableFuture.join(), HttpStatus.CREATED);
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
