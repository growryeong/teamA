package org.zerock.domain;

import lombok.Data;

@Data
public class ChallengeTask {
    private Long taskId;
    private Long challengeId;
    private String task;
}