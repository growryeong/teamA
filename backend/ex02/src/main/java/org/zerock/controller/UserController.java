package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.EmailChangeDTO;
import org.zerock.domain.LoginDTO;
import org.zerock.domain.PasswordChangeDTO;
import org.zerock.domain.UserDTO;
import org.zerock.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
	
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
    	try {
    		System.out.println("Received login data: " + loginDTO);
    		UserDTO user = userService.login(loginDTO.getUserId(), loginDTO.getPassword());
    		return ResponseEntity.ok().body(user);
    	} catch (Exception e) {
    		System.out.println(e);
    		return ResponseEntity.badRequest().body(e.getMessage());
    		
		}
    }
    
    // 비밀번호 변경
    @PostMapping("/{userId}/change-password")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> changePassword(
            @PathVariable(value = "userId", required = true) String userId,
            @RequestBody PasswordChangeDTO request) {
        System.out.println("비밀번호 변경 요청 - userId: " + userId);
        System.out.println("새로운 비밀번호: " + request.getPassword());

        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.badRequest().body("userId가 누락되었습니다.");
        }

        try {
            userService.updatePassword(userId, request.getPassword());
            return ResponseEntity.ok().body("비밀번호가 성공적으로 변경되었습니다.");
        } catch (Exception e) {
            System.err.println("비밀번호 변경 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호 변경 실패: " + e.getMessage());
        }
    }
   
    // 이메일 변경 추가
    @PostMapping("/{userId}/change-email")
    public ResponseEntity<?> changeEmail(
            @PathVariable("userId") String userId,
            @RequestBody EmailChangeDTO request) {
        System.out.println("이메일 변경 요청 - userId: " + userId);
        System.out.println("새로운 이메일: " + request.getEmail());

        try {
            userService.updateEmail(userId, request.getEmail());
            return ResponseEntity.ok().body("이메일이 성공적으로 변경되었습니다.");
        } catch (Exception e) {
            System.err.println("이메일 변경 실패: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
