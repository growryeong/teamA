package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.UserChallengeDTO;
import org.zerock.domain.UserDTO;
import org.zerock.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
    private UserMapper userMapper;
    
    @Override
    public void registerUser(UserDTO userDTO) throws Exception {
        // 중복 체크
        if (userMapper.checkUserId(userDTO.getUserId()) > 0) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (userMapper.checkEmail(userDTO.getEmail()) > 0) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        
        userMapper.insertUser(userDTO);
    }
    
    @Override
    public UserDTO login(String userId, String password) throws Exception {
        UserDTO user = userMapper.getUserByUserId(userId);
        
        if(user == null) {
            throw new Exception("아이디가 존재하지 않습니다.");
        }
        
        if(!user.getPassword().equals(password)) {
            throw new Exception("비밀번호가 일치하지 않습니다");
        }
        return user;
    }
    
    @Override
    public List<UserChallengeDTO> getUserChallenges(String userId) throws Exception {
        return userMapper.getUserChallenges(userId);
    }
    
    @Override
    public void updatePassword(String userId, String newPassword) throws Exception {
        UserDTO user = userMapper.getUserByUserId(userId);
        if (user == null) {
            throw new Exception("사용자를 찾을 수 없습니다.");
        }
        
        // 비밀번호 업데이트
        int result = userMapper.updatePassword(userId, newPassword);
        if (result == 0) {
            throw new Exception("비밀번호 변경에 실패했습니다.");
        }
    }
    
    @Override
    public void updateEmail(String userId, String newEmail) throws Exception {
        if (userMapper.checkEmail(newEmail) > 0) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        userMapper.updateEmail(userId, newEmail);
    }
}
