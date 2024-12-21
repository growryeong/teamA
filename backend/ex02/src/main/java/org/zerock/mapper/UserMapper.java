package org.zerock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.UserDTO;
import org.zerock.domain.User;
import org.zerock.domain.UserChallengeDTO;

/**
 * @brief 사용자 관리를 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 사용자 회원가입, 로그인, 정보 수정, 챌린지와 관련된
 * 데이터베이스 작업을 처리합니다.
 */
@Mapper
public interface UserMapper {

    /**
     * @brief 사용자 회원가입
     * 
     * 주어진 사용자 정보를 데이터베이스에 등록합니다.
     * 
     * @param user 등록할 사용자 객체
     */
    void insertUser(UserDTO user);

    /**
     * @brief 사용자 ID 중복 확인
     * 
     * 주어진 사용자 ID가 데이터베이스에 이미 존재하는지 확인합니다.
     * 
     * @param userId 확인할 사용자 ID
     * @return 중복된 사용자 ID의 개수
     */
    int checkUserId(String userId);

    /**
     * @brief 이메일 중복 확인
     * 
     * 주어진 이메일이 데이터베이스에 이미 존재하는지 확인합니다.
     * 
     * @param email 확인할 이메일
     * @return 중복된 이메일의 개수
     */
    int checkEmail(String email);

    /**
     * @brief 사용자 정보 조회
     * 
     * 주어진 사용자 ID를 기반으로 사용자 정보를 조회합니다.
     * 
     * @param userId 조회할 사용자 ID
     * @return 사용자 상세 정보 DTO
     */
    UserDTO getUserByUserId(String userId);

    /**
     * @brief 사용자 비밀번호 변경
     * 
     * 주어진 사용자 ID와 새 비밀번호를 사용하여 비밀번호를 업데이트합니다.
     * 
     * @param userId 사용자 ID
     * @param password 새 비밀번호
     * @return 업데이트 성공 여부 (성공 시 1, 실패 시 0)
     */
    int updatePassword(@Param("userId") String userId, @Param("password") String password);

    /**
     * @brief 사용자 이메일 변경
     * 
     * 주어진 사용자 ID와 새 이메일을 사용하여 이메일을 업데이트합니다.
     * 
     * @param userId 사용자 ID
     * @param email 새 이메일
     */
    void updateEmail(@Param("userId") String userId, @Param("email") String email);

    /**
     * @brief 사용자 챌린지 목록 조회
     * 
     * 주어진 사용자 ID를 기반으로 해당 사용자가 참여한 챌린지 목록을 조회합니다.
     * 
     * @param userId 사용자 ID
     * @return 사용자 챌린지 리스트
     */
    List<UserChallengeDTO> getUserChallenges(String userId);

    /**
     * @brief 사용자 ID로 사용자 조회
     * 
     * 주어진 사용자 ID를 기반으로 사용자 정보를 조회합니다.
     * 
     * @param userId 조회할 사용자 ID
     * @return 사용자 객체
     */
    User findById(String userId);
}
