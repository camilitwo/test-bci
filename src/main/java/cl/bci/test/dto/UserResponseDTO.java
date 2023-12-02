package cl.bci.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private Date created;
    private Date lastLogin;
    private String token;
    private Boolean isActive;
    private List<PhoneDTO> Phones;
}
