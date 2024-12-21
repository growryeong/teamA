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

/**
 * @brief 사용자 관리를 위한 REST 컨트롤러
 * 
 * 이 컨트롤러는 회원가입, 로그인, 비밀번호 변경, 이메일 변경 등
 * 사용자와 관련된 작업을 처리하기 위한 API를 제공합니다.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class UserController {

    /** 사용자 관리를 위한 서비스 */
    @Autowired
    private UserService userService;

    /**
     * @brief 사용자 회원가입
     * 
     * 사용자가 입력한 정보를 기반으로 새로운 사용자를 등록합니다.
     * 
     * @param userDTO 회원가입 요청 데이터
     * @return 성공 메시지 또는 오류 메시지
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * @brief 사용자 로그인
     * 
     * 사용자 ID와 비밀번호를 통해 로그인을 시도합니다.
     * 
     * @param loginDTO 로그인 요청 데이터
     * @return 로그인 성공 시 사용자 정보, 실패 시 오류 메시지
     */
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            System.out.println("로그인 요청 데이터: " + loginDTO);
            UserDTO user = userService.login(loginDTO.getUserId(), loginDTO.getPassword());
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * @brief 비밀번호 변경
     * 
     * 특정 사용자의 비밀번호를 변경합니다.
     * 
     * @param userId 변경할 사용자의 ID
     * @param request 새로운 비밀번호 요청 데이터
     * @return 성공 메시지 또는 오류 메시지
     */
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

    /**
     * @brief 이메일 변경
     * 
     * 특정 사용자의 이메일을 변경합니다.
     * 
     * @param userId 변경할 사용자의 ID
     * @param request 새로운 이메일 요청 데이터
     * @return 성공 메시지 또는 오류 메시지
     */
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
