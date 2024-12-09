package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.UserChallenge;

@Mapper
public interface UserChallengeMapper {

    void insertUserChallenge(UserChallenge userChallenge);

    // 필요에 따라 조회 메서드 추가
    UserChallenge findByUserAndChallenge(@Param("userId") String userId, @Param("challengeId") Long challengeId);

    List<UserChallenge> findByUser(@Param("userId") String userId);
    
    UserChallenge findOngoingChallenge(String userId);
    
    // 진행 중인 챌린지를 가져오는 쿼리
    List<UserChallenge> findOngoingChallengeByUserId(@Param("userId") String userId);
    
    
    // USER_CHALLENGE_ID로 챌린지 및 태스크 정보 가져오기
    Map<String, Object> getUserChallengeDetails(@Param("userChallengeId") Long userChallengeId);
    
}
