package com.jobportal.polaris_backend.entity;

import com.jobportal.polaris_backend.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.jobportal.polaris_backend.dto.AccountType;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="users")
public class UserEntity {
    @Id
    private Long id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private AccountType accountType;
    private Long profileId;

    public UserDTO toDTO(){
        return new UserDTO(this.id, this.name, this.email, this.password, this.accountType, this.profileId);
    }
}
