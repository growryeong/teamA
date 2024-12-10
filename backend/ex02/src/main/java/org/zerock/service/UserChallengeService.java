package org.zerock.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Challenge;
import org.zerock.domain.User;
import org.zerock.domain.UserChallenge;
import org.zerock.mapper.ChallengeMapper;
import org.zerock.mapper.UserChallengeMapper;
import org.zerock.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserChallengeService {
   private final UserChallengeMapper userChallengeMapper;
   private final ChallengeMapper challengeMapper;
   private final UserMapper userMapper;

   @Transactional
   public void saveUserChallenge(String userId, String challengeType, String challengeTitle, 
                               int duration, String startDate, String status) {
	    // 유저 존재 확인
	    User user = userMapper.findById(userId);
	    if (user == null) {
	        log.warn("User not found for userId={}", userId);
	        throw new RuntimeException("해당 유저를 찾을 수 없습니다. userId: " + userId);
	    }

	    // Challenge 조회
	    Map<String, Object> params = new HashMap<>();
	    params.put("title", challengeTitle);
	    params.put("activityTypeId", Integer.parseInt(challengeType));  // String을 Integer로 변환

	    Challenge challenge = challengeMapper.findByTitleAndType(params);
	    if (challenge == null) {
	        log.warn("Challenge not found for title={} and type={}", challengeTitle, challengeType);
	        throw new RuntimeException("해당 챌린지를 찾을 수 없습니다. title: " + challengeTitle + ", type: " + challengeType);
	    }

	    // Date Processing
	    LocalDate start = LocalDate.parse(startDate);
	    LocalDate end = start.plusDays(duration);
	    
	    UserChallenge userChallenge = new UserChallenge();
	    userChallenge.setUserId(userId);
	    userChallenge.setChallengeId(challenge.getChallengeId());
	    userChallenge.setProgress(0.0);
	    userChallenge.setStatus(status);
	    userChallenge.setStartDate(startDate);
	    userChallenge.setEndDate(end.toString());
	    userChallenge.setDuration(duration);  // duration 설정 추가해야 함

	    log.info("Prepared UserChallenge entity: {}", userChallenge);
	    
	    try {
	        userChallengeMapper.insertUserChallenge(userChallenge);
	        log.info("UserChallenge inserted successfully for userId={} and challengeId={}", userId, challenge.getChallengeId());
	    } catch (Exception e) {
	        log.error("Error inserting UserChallenge: {}", e.getMessage(), e);
	        throw new RuntimeException("챌린지 저장 중 오류가 발생했습니다.", e);
	    }
   }

   // 진행 중인 챌린지 가져오기
   public List<UserChallenge> getOngoingChallenge(String userId) {
       return userChallengeMapper.findOngoingChallengeByUserId(userId);
   }
}
