package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ChallengeType {
    private Long activityTypeId;
    private String name;
    private String icon;
}