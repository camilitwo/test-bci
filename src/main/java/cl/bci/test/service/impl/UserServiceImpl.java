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

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public ResponseEntity<UserResponseDTO> saveUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        Phone phone = new Phone();
        userRequestDTO.getPhones().forEach(phoneDTO -> {
            BeanUtils.copyProperties(phoneDTO, phone);
            user.getPhones().add(phone);
        });
        BeanUtils.copyProperties(userRepository.save(user), userResponseDTO);

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        List<UserResponseDTO> userResponseDTO = new ArrayList<>();
        BeanUtils.copyProperties(userRepository.findAll(), userResponseDTO);
        return userResponseDTO;
    }
}
