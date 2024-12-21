package org.zerock.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

/**
 * @brief 챌린지 및 기간 관리를 위한 REST 컨트롤러
 * 
 * 이 컨트롤러는 챌린지, 과제, 기간 관련 정보를 조회하거나
 * 새로운 챌린지를 생성하는 API를 제공합니다.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class ChallengeController {

    /** 챌린지 관리를 위한 서비스 */
    private final ChallengeService challengeService;
    /** 기간 관리를 위한 서비스 */
    private final DurationService durationService;

    /**
     * @brief 전체 챌린지 목록 조회
     * 
     * @return 모든 챌린지 정보를 담고 있는 ResponseEntity
     */
    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        return ResponseEntity.ok(challenges);
    }

    /**
     * @brief 특정 챌린지에 포함된 과제 목록 조회
     * 
     * @param id 조회할 챌린지의 ID
     * @return 해당 챌린지에 포함된 과제 리스트
     */
    @GetMapping("/challenges/{id}/tasks")
    public List<ChallengeTask> getTasks(@PathVariable Long id) {
        return challengeService.getTasksByChallengeId(id);
    }

    /**
     * @brief 가능한 기간 목록 조회
     * 
     * @return 모든 기간 정보를 담고 있는 리스트
     */
    @GetMapping("/durations")
    public List<Duration> getDurations() {
        return durationService.getAllDurations();
    }

    /**
     * @brief 새로운 챌린지 생성
     * 
     * @param request 생성할 챌린지의 정보를 담은 요청 객체
     * @return 생성된 챌린지 객체
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

    /**
     * @brief 전체 과제 목록 조회
     * 
     * @return 모든 과제를 담고 있는 ResponseEntity
     */
    @GetMapping("/tasks")
    public ResponseEntity<List<ChallengeTask>> getAllTasks() {
        List<ChallengeTask> tasks = challengeService.findAllTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * @brief 챌린지 생성을 위한 요청 객체
     * 
     * 이 클래스는 새로운 챌린지를 생성할 때 필요한 정보를 캡슐화합니다.
     */
    @Getter @Setter
    static class CreateChallengeRequest {
        /** 챌린지 유형 이름 */
        private String typeName;
        /** 챌린지 제목 */
        private String title;
        /** 챌린지 설명 */
        private String description;
        /** 챌린지 기간 (일 단위) */
        private int duration;
        /** 챌린지에 포함된 과제 리스트 */
        private List<String> tasks;
    }
}
