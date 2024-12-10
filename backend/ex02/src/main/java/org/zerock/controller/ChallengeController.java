package org.zerock.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Challenge;
import org.zerock.domain.ChallengeTask;
import org.zerock.domain.Duration;
import org.zerock.service.ChallengeService;
import org.zerock.service.DurationService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class ChallengeController {

    private final ChallengeService challengeService;
    private final DurationService durationService;

    /**
     * 전체 챌린지 목록 조회
     * GET /api/challenges
     */
    @GetMapping("/challenges")
    public List<Challenge> getChallenges() {
        return challengeService.getAllChallenges();
    }

    /**
     * 특정 챌린지의 과제 목록 조회
     * GET /api/challenges/{id}/tasks
     */
    @GetMapping("/challenges/{id}/tasks")
    public List<ChallengeTask> getTasks(@PathVariable Long id) {
        return challengeService.getTasksByChallengeId(id);
    }

    /**
     * 가능한 기간 목록 조회
     * GET /api/durations
     */
    @GetMapping("/durations")
    public List<Duration> getDurations() {
        return durationService.getAllDurations();
    }

    /**
     * 새로운 챌린지 생성
     * POST /api/challenges
     */
    @PostMapping("/challenges")
    public Challenge createChallenge(@RequestBody CreateChallengeRequest request) {
        return challengeService.createChallenge(
            request.getTypeName(),
            request.getTitle(),
            request.getDescription(),
            request.getDuration(),
            request.getTasks()
        );
    }

    @Getter @Setter
    static class CreateChallengeRequest {
        private String typeName;
        private String title;
        private String description;
        private int duration;
        private List<String> tasks;
    }
}