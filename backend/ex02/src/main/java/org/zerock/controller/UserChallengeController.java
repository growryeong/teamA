package org.zerock.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserChallengeController {
	 private final UserChallengeService userChallengeService;

	 @PostMapping("/userChallenges")
	 public ResponseEntity<String> saveUserChallenge(@RequestBody UserChallengeRequest request) {
	     // 로그 추가
	     System.out.println("Received Request: " + request);

	     userChallengeService.saveUserChallenge(
	    	        request.getUser_id(),
	    	        request.getChallengeType(),
	    	        request.getChallengeTitle(),
	    	        request.getDuration(),
	    	        request.getStartDate(),
	    	        request.getStatus()
	     );

	     return ResponseEntity.ok("챌린지가 저장되었습니다!");
	 }
	 
	 @GetMapping("/userChallenges/{userId}")
	 public ResponseEntity<List<UserChallenge>> getOngoingChallenge(@PathVariable String userId) {
	     List<UserChallenge> ongoingChallenges = userChallengeService.getOngoingChallenge(userId);
	     if (ongoingChallenges.isEmpty()) {
	         return ResponseEntity.status(404).build();
	     }
	     return ResponseEntity.ok(ongoingChallenges);
	 }

	 	@ToString
	    @Getter @Setter
	    static class UserChallengeRequest {
	        private String user_id;
	        private String challengeType;
	        private String challengeTitle;
	        private int duration;
	        private String startDate;
	        private String status;
	    }
	}

