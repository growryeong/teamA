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

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeMapper challengeMapper;
    private final ChallengeTaskMapper challengeTaskMapper;
    private final ChallengeTypeMapper challengeTypeMapper;

    public List<Challenge> getAllChallenges() {
        return challengeMapper.findAll();
    }

    public List<ChallengeTask> getTasksByChallengeId(Long challengeId) {
        return challengeTaskMapper.findByChallengeId(challengeId);
    }

    @Transactional
    public Challenge createChallenge(String typeName, String title, String description, int duration, List<String> tasks) {
        ChallengeType type = challengeTypeMapper.findByName(typeName);
        if (type == null) {
            throw new RuntimeException("해당 유형을 찾을 수 없습니다: " + typeName);
        }

        // Challenge insert
        Challenge challenge = new Challenge();
        challenge.setTitle(title);
        challenge.setDescription(description);
        challenge.setDuration(duration);
        challenge.setActivityTypeId(type.getActivityTypeId());
        challengeMapper.insert(challenge); // Insert 후 challengeId가 keyProperty에 의해 매핑됨

        // Tasks insert
        for (String t : tasks) {
            ChallengeTask task = new ChallengeTask();
            task.setChallengeId(challenge.getChallengeId());
            task.setTask(t);
            challengeTaskMapper.insert(task);
        }

        return challenge;
    }
}

