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
		// �ߺ� üũ
		if (userMapper.checkUserId(userDTO.getUserId())>0) {
			throw new Exception("���̵� �̹� ����");
		}
		if (userMapper.checkEmail(userDTO.getEmail()) >0) {
			throw new Exception("�̹� ���K�ϴ� �̸���");
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
