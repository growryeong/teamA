package org.zerock.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserChallenge {
    private Long userChallengeId;
    private String userId;
    private Long challengeId;
    private Double progress;
    private String status;
    private String startDate;
    private String endDate;
    private String challengeTitle;
    private Integer duration;
    private String task;  // 추가
}