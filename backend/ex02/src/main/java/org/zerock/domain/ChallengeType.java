package org.zerock.domain;


import lombok.Data;


/**
 * @brief 챌린지 유형(ChallengeType) 도메인 클래스
 * 
 * 이 클래스는 챌린지 유형에 대한 정보를 나타내며, 데이터베이스와 매핑됩니다.
 */
@Data
public class ChallengeType {

    /**
     * @brief 활동 유형 ID
     * 
     * 챌린지 유형을 고유하게 식별하기 위한 ID입니다.
     */
    private Long activityTypeId;

    /**
     * @brief 챌린지 유형 이름
     * 
     * 챌린지 유형의 이름을 나타냅니다.
     */
    private String name;

    /**
     * @brief 챌린지 유형 아이콘
     * 
     * 챌린지 유형과 연관된 아이콘 파일명 또는 경로를 나타냅니다.
     */
    private String icon;
}
