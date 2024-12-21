package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Users;

@Mapper
public interface UserMapper {
	void insertUser(Users user);
	int checkUserId(String userId);
	int checkEmail(String email);
}
