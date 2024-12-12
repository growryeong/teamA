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
   public void saveUserChallenge(String userId,Long challengeId, String challengeType, String challengeTitle, 
                                  int duration, String startDate, String status, Long taskId) {
       User user = userMapper.findById(userId);
       if (user == null) {
           log.warn("User not found for userId={}", userId);
           throw new RuntimeException("해당 유저를 찾을 수 없습니다. userId: " + userId);
       }

       Map<String, Object> params = new HashMap<>();
       params.put("title", challengeTitle);
       params.put("activityTypeId", Integer.parseInt(challengeType));

       Challenge challenge = challengeMapper.findByTitleAndType(params);
       if (challenge == null) {
           log.warn("Challenge not found for title={} and type={}", challengeTitle, challengeType);
           throw new RuntimeException("해당 챌린지를 찾을 수 없습니다. title: " + challengeTitle + ", type: " + challengeType);
       }

       LocalDate start = LocalDate.parse(startDate);
       LocalDate end = start.plusDays(duration);

       UserChallenge userChallenge = new UserChallenge();
       userChallenge.setUserId(userId);
       userChallenge.setChallengeId(challenge.getChallengeId());
       userChallenge.setProgress(0.0);
       userChallenge.setStatus(status);
       userChallenge.setStartDate(startDate);
       userChallenge.setEndDate(end.toString());
       userChallenge.setDuration(duration);
       userChallenge.setTaskId(taskId);

       userChallengeMapper.insertUserChallenge(userChallenge);
   }

   // 진행 중인 챌린지 가져오기
   public List<UserChallenge> getOngoingChallenge(String userId) {
       return userChallengeMapper.findOngoingChallengeByUserId(userId);
   }
   
   public Map<String, Object> getUserChallengeDetails(Long userChallengeId) {
       return userChallengeMapper.getUserChallengeDetails(userChallengeId);
   }
   // 중복 체크 로직
   public boolean isChallengeAlreadyParticipated(String userId, Long taskId) {
	    List<UserChallenge> userChallenges = userChallengeMapper.findByUser(userId);

	    return userChallenges.stream()
	                         .anyMatch(challenge -> challenge.getTaskId().equals(taskId));
	}
}
