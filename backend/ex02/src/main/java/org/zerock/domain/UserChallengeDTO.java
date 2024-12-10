package org.zerock.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserChallengeDTO {
    private String userId;
    private Long challengeId;
    private Double progress;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

}
