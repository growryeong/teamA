package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.UserChallengeDTO;
import org.zerock.domain.UserDTO;
import org.zerock.mapper.UserMapper;

/**
 * @brief 사용자 관리를 위한 서비스 구현 클래스
 * 
 * 이 클래스는 회원가입, 로그인, 사용자 정보 수정 등 사용자와 관련된
 * 주요 비즈니스 로직을 구현합니다.
 */
@Service
public class UserServiceImpl implements UserService {

    /** 사용자 데이터를 관리하는 Mapper */
    @Autowired
    private UserMapper userMapper;

    /**
     * @brief 사용자 회원가입
     * 
     * 주어진 사용자 정보를 기반으로 새로운 사용자를 등록합니다.
     * 
     * @param userDTO 회원가입 요청 데이터
     * @throws Exception 아이디 또는 이메일 중복 시 예외 발생
     */
    @Override
    public void registerUser(UserDTO userDTO) throws Exception {
        // 중복 체크
        if (userMapper.checkUserId(userDTO.getUserId()) > 0) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (userMapper.checkEmail(userDTO.getEmail()) > 0) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        // 사용자 등록
        userMapper.insertUser(userDTO);
    }

    /**
     * @brief 사용자 로그인
     * 
     * 주어진 아이디와 비밀번호를 통해 로그인 인증을 수행합니다.
     * 
     * @param userId 사용자 ID
     * @param password 사용자 비밀번호
     * @return 인증된 사용자 정보
     * @throws Exception 아이디가 없거나 비밀번호가 일치하지 않을 경우
     */
    @Override
    public UserDTO login(String userId, String password) throws Exception {
        // 사용자 조회
        UserDTO user = userMapper.getUserByUserId(userId);

        if (user == null) {
            throw new Exception("아이디가 존재하지 않습니다.");
        }

        if (!user.getPassword().equals(password)) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    /**
     * @brief 사용자 챌린지 목록 조회
     * 
     * 특정 사용자의 챌린지 목록을 조회합니다.
     * 
     * @param userId 사용자 ID
     * @return 사용자 챌린지 리스트
     * @throws Exception 데이터 조회 중 오류 발생 시
     */
    @Override
    public List<UserChallengeDTO> getUserChallenges(String userId) throws Exception {
        return userMapper.getUserChallenges(userId);
    }

    /**
     * @brief 사용자 비밀번호 변경
     * 
     * 주어진 사용자 ID와 새로운 비밀번호를 기반으로 비밀번호를 변경합니다.
     * 
     * @param userId 사용자 ID
     * @param newPassword 새 비밀번호
     * @throws Exception 사용자 정보를 찾을 수 없거나 업데이트 실패 시
     */
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

    /**
     * @brief 사용자 이메일 변경
     * 
     * 주어진 사용자 ID와 새로운 이메일을 기반으로 이메일을 변경합니다.
     * 
     * @param userId 사용자 ID
     * @param newEmail 새 이메일
     * @throws Exception 이메일이 중복되거나 업데이트 실패 시
     */
    @Override
    public void updateEmail(String userId, String newEmail) throws Exception {
        // 이메일 중복 체크
        if (userMapper.checkEmail(newEmail) > 0) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        // 이메일 업데이트
        userMapper.updateEmail(userId, newEmail);
    }
}
