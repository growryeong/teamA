package org.zerock.service;

import java.util.List;

import org.zerock.domain.User;
import org.zerock.domain.UserChallengeDTO;
import org.zerock.domain.UserDTO;

/**
 * @brief 사용자 관련 비즈니스 로직 인터페이스
 * 
 * 이 인터페이스는 사용자 회원가입, 로그인, 정보 수정, 챌린지 조회 등
 * 사용자와 관련된 주요 서비스 기능을 정의합니다.
 */
public interface UserService {

    /**
     * @brief 사용자 회원가입
     * 
     * 주어진 사용자 정보를 기반으로 새로운 사용자를 등록합니다.
     * 
     * @param userDTO 회원가입 요청 데이터를 포함하는 DTO
     * @throws Exception 아이디나 이메일이 중복된 경우 예외 발생
     */
    void registerUser(UserDTO userDTO) throws Exception;

    /**
     * @brief 사용자 로그인
     * 
     * 주어진 사용자 ID와 비밀번호를 통해 인증을 수행합니다.
     * 
     * @param userId 사용자 ID
     * @param password 사용자 비밀번호
     * @return 인증된 사용자의 상세 정보를 포함한 DTO
     * @throws Exception 사용자 ID가 없거나 비밀번호가 일치하지 않을 경우 예외 발생
     */
    UserDTO login(String userId, String password) throws Exception;

    /**
     * @brief 사용자 챌린지 목록 조회
     * 
     * 특정 사용자가 참여한 챌린지 목록을 조회합니다.
     * 
     * @param userId 조회할 사용자 ID
     * @return 사용자가 참여한 챌린지 정보를 포함한 리스트
     * @throws Exception 데이터 조회 중 오류가 발생한 경우 예외 발생
     */
    List<UserChallengeDTO> getUserChallenges(String userId) throws Exception;

    /**
     * @brief 사용자 비밀번호 변경
     * 
     * 주어진 사용자 ID와 새로운 비밀번호를 기반으로 비밀번호를 변경합니다.
     * 
     * @param userId 사용자 ID
     * @param newPassword 새로운 비밀번호
     * @throws Exception 사용자 정보가 없거나 업데이트에 실패한 경우 예외 발생
     */
    void updatePassword(String userId, String newPassword) throws Exception;

    /**
     * @brief 사용자 이메일 변경
     * 
     * 주어진 사용자 ID와 새로운 이메일을 기반으로 이메일을 변경합니다.
     * 
     * @param userId 사용자 ID
     * @param newEmail 새로운 이메일
     * @throws Exception 이메일이 중복되거나 업데이트에 실패한 경우 예외 발생
     */
    void updateEmail(String userId, String newEmail) throws Exception;
}
