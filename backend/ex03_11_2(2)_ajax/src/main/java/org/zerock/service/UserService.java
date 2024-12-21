package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Users;
import org.zerock.domain.UsersDTO;
import org.zerock.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public void registerUser(UsersDTO userDTO) throws Exception {
		// 중복 체크
		if (userMapper.checkUserId(userDTO.getUserId())>0) {
			throw new Exception("아이디가 이미 존재");
		}
		if (userMapper.checkEmail(userDTO.getEmail()) >0) {
			throw new Exception("이미 존쟇하는 이메일");
		}
		
		Users user = new Users();
		user.setUserId(userDTO.getUserId());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setUsername(userDTO.getUsername());
		user.setIsAdmin(0);
		
		userMapper.insertUser(user);
	}
	

}
