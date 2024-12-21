package org.zerock.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.UserChallenge;
import org.zerock.service.UserChallengeService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @brief 사용자 챌린지 관리를 위한 REST 컨트롤러
 * 
 * 이 컨트롤러는 사용자 챌린지 등록, 조회 및 참여와 관련된 API를 제공합니다.
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class UserChallengeController {

    /** 사용자 챌린지 관리를 위한 서비스 */
    private final UserChallengeService userChallengeService;

    /**
     * @brief 사용자 챌린지 저장
     * 
     * 새로운 사용자 챌린지를 저장합니다.
     * 
     * @param request 사용자 챌린지 생성을 위한 요청 객체
     * @return 저장 성공 또는 실패 메시지
     */
    @PostMapping("/userChallenges")
    public ResponseEntity<String> saveUserChallenge(@RequestBody UserChallengeRequest request) {
        try {
            log.info("요청 데이터: {}", request);

            // 사용자 챌린지 저장 서비스 호출
            userChallengeService.saveUserChallenge(
                request.getUser_id(),
                request.getChallengeId(),
                request.getChallengeType(),
                request.getChallengeTitle(),
                request.getDuration(),
                request.getStartDate(),
                request.getStatus(),
                request.getTask_id()
            );

            return ResponseEntity.ok("챌린지가 성공적으로 저장되었습니다!");
        } catch (Exception e) {
            log.error("챌린지 저장 중 오류 발생: ", e);

            // 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("챌린지 저장 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * @brief 특정 사용자의 진행 중인 챌린지 조회
     * 
     * 사용자의 ID를 기준으로 진행 중인 챌린지를 조회합니다.
     * 
     * @param userId 조회할 사용자의 ID
     * @return 진행 중인 챌린지 목록
     */
    @GetMapping("/userChallenges/{userId}")
    public ResponseEntity<List<UserChallenge>> getOngoingChallenge(@PathVariable String userId) {
        // 진행 중인 챌린지 목록 조회
        List<UserChallenge> ongoingChallenges = userChallengeService.getOngoingChallenge(userId);

        if (ongoingChallenges.isEmpty()) {
            // 진행 중인 챌린지가 없을 경우 404 응답
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 진행 중인 챌린지 목록 반환
        return ResponseEntity.ok(ongoingChallenges);
    }

    /**
     * @brief 특정 사용자 챌린지의 상세 정보 조회
     * 
     * 사용자 챌린지 ID를 기준으로 상세 정보를 조회합니다.
     * 
     * @param userChallengeId 조회할 사용자 챌린지 ID
     * @return 사용자 챌린지의 상세 정보
     */
    @GetMapping("/{userChallengeId}")
    public ResponseEntity<Map<String, Object>> getUserChallengeDetails(@PathVariable Long userChallengeId) {
        try {
            // 사용자 챌린지 상세 정보 조회
            Map<String, Object> result = userChallengeService.getUserChallengeDetails(userChallengeId);

            // 상세 정보 반환
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("챌린지 상세 정보 조회 중 오류 발생: ", e);

            // 오류 발생 시 500 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * @brief 사용자 챌린지에 참여
     * 
     * 기존 챌린지 정보를 복제하여 새로운 사용자로 저장합니다.
     * 
     * @param request 챌린지 참여 요청 데이터
     * @return 성공 또는 실패 메시지
     */
    @PostMapping("/userChallenges/join")
    public ResponseEntity<String> joinChallenge(@RequestBody Map<String, Object> request) {
        try {
            log.info("챌린지 참여 요청 데이터: {}", request);

            // 요청 데이터 파싱
            Long userChallengeId = ((Number) request.get("userChallengeId")).longValue();
            String newUserId = (String) request.get("userId");

            // 기존 챌린지 데이터 조회
            Map<String, Object> originalChallenge = userChallengeService.getUserChallengeDetails(userChallengeId);
            if (originalChallenge == null || originalChallenge.isEmpty()) {
                log.warn("유효한 챌린지 데이터를 찾을 수 없습니다. userChallengeId: {}", userChallengeId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("유효한 챌린지 데이터를 찾을 수 없습니다.");
            }

            // 데이터 추출 및 변환
            Long challengeId = ((BigDecimal) originalChallenge.get("CHALLENGEID")).longValue();
            Long taskId = ((BigDecimal) originalChallenge.get("TASKID")).longValue();
            String challengeTitle = (String) originalChallenge.get("CHALLENGETITLE");
            Integer duration = ((BigDecimal) originalChallenge.get("DURATION")).intValue();

            // 새로운 사용자 챌린지 생성
            userChallengeService.saveUserChallenge(
                newUserId,
                challengeId,
                null, // 챌린지 유형은 null 처리
                challengeTitle,
                duration,
                LocalDate.now().toString(), // 현재 날짜를 시작 날짜로 설정
                "in_progress", // 상태 설정
                taskId
            );

            return ResponseEntity.ok("챌린지 참여가 성공적으로 완료되었습니다.");
        } catch (Exception e) {
            log.error("챌린지 참여 중 오류 발생: ", e);

            // 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("챌린지 참여 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * @brief 사용자 챌린지 생성 요청 데이터
     * 
     * 사용자 챌린지 생성을 위한 요청 데이터를 캡슐화합니다.
     */
    @Getter @Setter @ToString
    static class UserChallengeRequest {
        /** 사용자 ID */
        private String user_id;
        /** 챌린지 유형 */
        private String challengeType;
        /** 챌린지 ID */
        private Long challengeId;
        /** 챌린지 제목 */
        private String challengeTitle;
        /** 챌린지 기간 */
        private int duration;
        /** 시작 날짜 */
        private String startDate;
        /** 챌린지 상태 */
        private String status;
        /** 과제 ID */
        private Long task_id;
    }
}
