package org.zerock.domain;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String password;
    private String username;
    private String email;
}
