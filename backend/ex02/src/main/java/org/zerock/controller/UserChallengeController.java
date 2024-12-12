package org.zerock.controller;

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

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserChallengeController {
	 private final UserChallengeService userChallengeService;

	 @PostMapping("/userChallenges")
	 public ResponseEntity<String> saveUserChallenge(@RequestBody UserChallengeRequest request) {
	     try {
	         log.info("Received Request: {}", request);

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
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                              .body("챌린지 저장 중 오류 발생: " + e.getMessage());
	     }
	 }
	    
	    @GetMapping("/userChallenges/{userId}")
	    public ResponseEntity<List<UserChallenge>> getOngoingChallenge(@PathVariable String userId) {
	        List<UserChallenge> ongoingChallenges = userChallengeService.getOngoingChallenge(userId);
	        if (ongoingChallenges.isEmpty()) {
	            return ResponseEntity.status(404).build();
	        }
	        return ResponseEntity.ok(ongoingChallenges);
	    }
	    
	    @GetMapping("/{userChallengeId}")
	    public ResponseEntity<Map<String, Object>> getUserChallengeDetails(@PathVariable Long userChallengeId) {
	        try {
	            Map<String, Object> result = userChallengeService.getUserChallengeDetails(userChallengeId);
	            return ResponseEntity.ok(result);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
	    
	    

	    @Getter @Setter @ToString
	    static class UserChallengeRequest {
	        private String user_id;
	        private String challengeType;
	        private Long challengeId;
	        private String challengeTitle;
	        private int duration;
	        private String startDate;
	        private String status;
	        private Long task_id; // JSON 필드명에 맞게 수정
	    }
	}

