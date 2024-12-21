package org.zerock.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Users {
	private String userId;
	private String password;
	private String username;
	private LocalDateTime createdAt;
	private String email;
	private Integer isAdmin;
}
