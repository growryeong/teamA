package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Challenge;
import org.zerock.domain.ChallengeTask;
import org.zerock.domain.ChallengeType;
import org.zerock.mapper.ChallengeMapper;
import org.zerock.mapper.ChallengeTaskMapper;
import org.zerock.mapper.ChallengeTypeMapper;

import lombok.RequiredArgsConstructor;

/**
 * @brief 챌린지 관리 서비스
 * 
 * 이 서비스는 챌린지와 관련된 데이터를 처리하며,
 * 챌린지 목록 조회, 과제 조회 및 새로운 챌린지 생성을 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class ChallengeService {

    /** 챌린지 데이터를 관리하는 Mapper */
    private final ChallengeMapper challengeMapper;
    /** 챌린지 과제 데이터를 관리하는 Mapper */
    private final ChallengeTaskMapper challengeTaskMapper;
    /** 챌린지 유형 데이터를 관리하는 Mapper */
    private final ChallengeTypeMapper challengeTypeMapper;

    /**
     * @brief 전체 챌린지 목록 조회
     * 
     * 데이터베이스에 등록된 모든 챌린지 정보를 조회합니다.
     * 
     * @return 모든 챌린지의 리스트
     */
    public List<Challenge> getAllChallenges() {
        return challengeMapper.findAll();
    }

    /**
     * @brief 전체 과제 목록 조회
     * 
     * 데이터베이스에 등록된 모든 챌린지 과제를 조회합니다.
     * 
     * @return 모든 챌린지 과제의 리스트
     */
    public List<ChallengeTask> findAllTasks() {
        return challengeTaskMapper.findAllTasks();
    }

    /**
     * @brief 특정 챌린지의 과제 목록 조회
     * 
     * 주어진 챌린지 ID에 해당하는 모든 과제를 조회합니다.
     * 
     * @param challengeId 과제를 조회할 챌린지의 ID
     * @return 해당 챌린지의 과제 리스트
     */
    public List<ChallengeTask> getTasksByChallengeId(Long challengeId) {
        return challengeTaskMapper.findByChallengeId(challengeId);
    }

    /**
     * @brief 새로운 챌린지 생성
     * 
     * 주어진 데이터로 새로운 챌린지를 생성하며, 트랜잭션 내에서
     * 챌린지와 해당 과제를 데이터베이스에 삽입합니다.
     * 
     * @param typeName 챌린지 유형 이름
     * @param title 챌린지 제목
     * @param description 챌린지 설명
     * @param duration 챌린지 기간
     * @param tasks 챌린지에 포함될 과제 목록
     * @return 생성된 챌린지 객체
     * @throws RuntimeException 챌린지 유형이 존재하지 않을 경우
     */
    @Transactional
    public Challenge createChallenge(String typeName, String title, String description, int duration, List<String> tasks) {
        // 챌린지 유형 조회
        ChallengeType type = challengeTypeMapper.findByName(typeName);
        if (type == null) {
            throw new RuntimeException("해당 유형을 찾을 수 없습니다: " + typeName);
        }

        // 새로운 챌린지 생성 및 삽입
        Challenge challenge = new Challenge();
        challenge.setTitle(title);
        challenge.setDescription(description);
        challenge.setDuration(duration);
        challenge.setActivityTypeId(type.getActivityTypeId());
        challengeMapper.insert(challenge); // Insert 후 challengeId가 keyProperty에 의해 매핑됨

        // 과제 삽입
        for (String t : tasks) {
            ChallengeTask task = new ChallengeTask();
            task.setChallengeId(challenge.getChallengeId());
            task.setTask(t);
            challengeTaskMapper.insert(task);
        }

        return challenge;
    }
}
