package org.zerock.domain;

import lombok.Data;

@Data
public class Challenge {
    private Long challengeId;
    private String title;
    private String description;
    private Integer duration;
    private Long activityTypeId; // FK 참조
}
