package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.User;
import org.zerock.domain.UserChallengeDTO;
import org.zerock.domain.UserDTO;
import org.zerock.mapper.UserMapper;


public interface UserService {
    void registerUser(UserDTO userDTO) throws Exception;
    UserDTO login(String userId, String password) throws Exception;
    List<UserChallengeDTO> getUserChallenges(String userId) throws Exception;
    void updatePassword(String userId, String newPassword) throws Exception;
    void updateEmail(String userId, String newEmail) throws Exception;
    
}
