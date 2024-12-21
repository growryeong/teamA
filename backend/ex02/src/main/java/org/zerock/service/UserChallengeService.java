package org.zerock.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.User;
import org.zerock.domain.UserChallenge;
import org.zerock.mapper.ChallengeMapper;
import org.zerock.mapper.UserChallengeMapper;
import org.zerock.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @brief 사용자 챌린지 관리를 위한 서비스
 * 
 * 이 서비스는 사용자 챌린지 생성, 중복 확인, 진행 중인 챌린지 조회,
 * 데이터 복제 등의 비즈니스 로직을 처리합니다.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserChallengeService {

    /** 사용자 챌린지 관리를 위한 Mapper */
    private final UserChallengeMapper userChallengeMapper;

    /** 챌린지 관리를 위한 Mapper */
    private final ChallengeMapper challengeMapper;

    /** 사용자 관리를 위한 Mapper */
    private final UserMapper userMapper;

    /**
     * @brief 새로운 사용자 챌린지 저장
     * 
     * 주어진 데이터를 기반으로 사용자 챌린지를 생성하여 데이터베이스에 저장합니다.
     * 
     * @param userId 사용자 ID
     * @param challengeId 챌린지 ID
     * @param challengeType 챌린지 유형
     * @param challengeTitle 챌린지 제목
     * @param duration 챌린지 기간
     * @param startDate 시작 날짜
     * @param status 챌린지 상태
     * @param taskId 과제 ID
     * @throws RuntimeException 사용자 또는 챌린지 데이터가 유효하지 않을 경우
     */
    @Transactional
    public void saveUserChallenge(String userId, Long challengeId, String challengeType, String challengeTitle, 
                                  int duration, String startDate, String status, Long taskId) {
        int activityTypeId = 0;
        if (challengeType != null) {
            try {
                activityTypeId = Integer.parseInt(challengeType);
            } catch (NumberFormatException e) {
                log.warn("유효하지 않은 챌린지 유형: {}, 기본값 0으로 설정", challengeType);
            }
        }

        User user = userMapper.findById(userId);
        if (user == null) {
            throw new RuntimeException("유저를 찾을 수 없습니다. userId: " + userId);
        }

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = start.plusDays(duration);

        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setUserId(userId);
        userChallenge.setChallengeId(challengeId);
        userChallenge.setProgress(0.0);
        userChallenge.setStatus(status);
        userChallenge.setStartDate(startDate);
        userChallenge.setEndDate(end.toString());
        userChallenge.setDuration(duration);
        userChallenge.setTaskId(taskId);

        log.info("새로운 사용자 챌린지 저장: {}", userChallenge);
        userChallengeMapper.insertUserChallenge(userChallenge);
    }

    /**
     * @brief 기존 사용자 챌린지를 복제하여 새로운 사용자로 저장
     * 
     * 기존 챌린지 데이터를 기반으로 새로운 사용자에 대해 복제된 챌린지를 생성합니다.
     * 
     * @param originalUserChallengeId 기존 사용자 챌린지 ID
     * @param newUserId 새로운 사용자의 ID
     * @throws RuntimeException 유효한 챌린지 데이터를 찾을 수 없는 경우
     */
    @Transactional
    public void duplicateUserChallenge(Long originalUserChallengeId, String newUserId) {
        Map<String, Object> originalChallenge = getUserChallengeDetails(originalUserChallengeId);
        if (originalChallenge == null || originalChallenge.isEmpty()) {
            throw new RuntimeException("유효한 유저 챌린지 데이터를 찾을 수 없습니다.");
        }

        Long challengeId = ((Number) originalChallenge.get("CHALLENGEID")).longValue();
        Long taskId = ((Number) originalChallenge.get("TASKID")).longValue();
        int duration = ((Number) originalChallenge.get("DURATION")).intValue();
        String challengeTitle = (String) originalChallenge.get("CHALLENGETITLE");

        UserChallenge newChallenge = new UserChallenge();
        newChallenge.setUserId(newUserId);
        newChallenge.setChallengeId(challengeId);
        newChallenge.setTaskId(taskId);
        newChallenge.setDuration(duration);
        newChallenge.setStartDate(LocalDate.now().toString());
        newChallenge.setEndDate(LocalDate.now().plusDays(duration).toString());
        newChallenge.setStatus("in_progress");
        newChallenge.setProgress(0.0);

        userChallengeMapper.insertUserChallenge(newChallenge);
    }

    /**
     * @brief 진행 중인 사용자 챌린지 조회
     * 
     * 특정 사용자가 진행 중인 챌린지 목록을 조회합니다.
     * 
     * @param userId 조회할 사용자의 ID
     * @return 진행 중인 사용자 챌린지 리스트
     */
    public List<UserChallenge> getOngoingChallenge(String userId) {
        return userChallengeMapper.findOngoingChallengeByUserId(userId);
    }

    /**
     * @brief 사용자 챌린지 상세 정보 조회
     * 
     * 특정 사용자 챌린지 ID를 기반으로 상세 정보를 조회합니다.
     * 
     * @param userChallengeId 조회할 사용자 챌린지 ID
     * @return 사용자 챌린지 상세 정보를 포함한 Map
     */
    public Map<String, Object> getUserChallengeDetails(Long userChallengeId) {
        return userChallengeMapper.getUserChallengeDetails(userChallengeId);
    }

    /**
     * @brief 사용자 챌린지 참여 중복 여부 확인
     * 
     * 특정 사용자와 과제 ID를 기반으로 이미 참여 중인지 확인합니다.
     * 
     * @param userId 사용자 ID
     * @param taskId 과제 ID
     * @return 이미 참여 중이면 true, 그렇지 않으면 false
     */
    public boolean isChallengeAlreadyParticipated(String userId, Long taskId) {
        List<UserChallenge> userChallenges = userChallengeMapper.findByUser(userId);
        return userChallenges.stream()
                             .anyMatch(challenge -> challenge.getTaskId().equals(taskId));
    }
}
