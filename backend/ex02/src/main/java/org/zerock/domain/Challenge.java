package org.zerock.domain;

import lombok.Data;

/**
 * @brief 챌린지 도메인 클래스
 * 
 * 이 클래스는 챌린지의 기본 정보를 나타내며, 데이터베이스와 매핑됩니다.
 */
@Data
public class Challenge {

    /**
     * @brief 챌린지 ID
     * 
     * 챌린지를 고유하게 식별하기 위한 ID입니다.
     */
    private Long challengeId;

    /**
     * @brief 챌린지 제목
     * 
     * 챌린지의 제목 또는 이름을 나타냅니다.
     */
    private String title;

    /**
     * @brief 챌린지 설명
     * 
     * 챌린지에 대한 간략한 설명을 제공합니다.
     */
    private String description;

    /**
     * @brief 챌린지 기간
     * 
     * 챌린지가 진행되는 기간(일 단위)을 나타냅니다.
     */
    private Integer duration;

    /**
     * @brief 활동 유형 ID
     * 
     * 챌린지의 유형을 나타내는 외래 키(FK)로, `ChallengeType` 테이블을 참조합니다.
     */
    private Long activityTypeId;
}
