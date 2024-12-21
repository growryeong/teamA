package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.UserChallenge;

/**
 * @brief 사용자 챌린지 관리를 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 사용자 챌린지의 등록, 조회, 진행 상태 확인 등과 관련된
 * 데이터베이스 작업을 처리합니다.
 */
@Mapper
public interface UserChallengeMapper {

    /**
     * @brief 사용자 챌린지 등록
     * 
     * 주어진 사용자 챌린지 객체를 데이터베이스에 삽입합니다.
     * 
     * @param userChallenge 등록할 사용자 챌린지 객체
     */
    void insertUserChallenge(UserChallenge userChallenge);

    /**
     * @brief 사용자와 챌린지 ID로 사용자 챌린지 조회
     * 
     * 특정 사용자 ID와 챌린지 ID를 기반으로 사용자 챌린지를 조회합니다.
     * 
     * @param userId 사용자 ID
     * @param challengeId 챌린지 ID
     * @return 해당 사용자 챌린지 객체
     */
    UserChallenge findByUserAndChallenge(@Param("userId") String userId, @Param("challengeId") Long challengeId);

    /**
     * @brief 특정 사용자의 모든 챌린지 조회
     * 
     * 사용자 ID를 기반으로 해당 사용자의 모든 챌린지를 조회합니다.
     * 
     * @param userId 사용자 ID
     * @return 사용자 챌린지 리스트
     */
    List<UserChallenge> findByUser(@Param("userId") String userId);

    /**
     * @brief 특정 사용자의 진행 중인 챌린지 조회
     * 
     * 사용자 ID를 기반으로 진행 중인 챌린지를 단일 조회합니다.
     * 
     * @param userId 사용자 ID
     * @return 진행 중인 사용자 챌린지 객체
     */
    UserChallenge findOngoingChallenge(String userId);

    /**
     * @brief 특정 사용자의 진행 중인 챌린지 목록 조회
     * 
     * 사용자 ID를 기반으로 진행 중인 모든 챌린지 목록을 조회합니다.
     * 
     * @param userId 사용자 ID
     * @return 진행 중인 사용자 챌린지 리스트
     */
    List<UserChallenge> findOngoingChallengeByUserId(@Param("userId") String userId);

    /**
     * @brief 사용자 챌린지 ID로 상세 정보 조회
     * 
     * 사용자 챌린지 ID를 기반으로 챌린지 및 태스크의 상세 정보를 조회합니다.
     * 
     * @param userChallengeId 사용자 챌린지 ID
     * @return 챌린지 및 태스크 상세 정보 Map
     */
    Map<String, Object> getUserChallengeDetails(@Param("userChallengeId") Long userChallengeId);
}
