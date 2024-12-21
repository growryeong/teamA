package org.zerock.domain;

import lombok.Data;

/**
 * @brief 사용자 챌린지(UserChallenge) 도메인 클래스
 * 
 * 이 클래스는 사용자가 참여 중인 챌린지의 정보를 나타내며, 데이터베이스와 매핑됩니다.
 */
@Data
public class UserChallenge {

    /**
     * @brief 사용자 챌린지 ID
     * 
     * 사용자 챌린지를 고유하게 식별하기 위한 ID입니다.
     */
    private Long userChallengeId;

    /**
     * @brief 사용자 ID
     * 
     * 챌린지에 참여 중인 사용자의 ID입니다.
     */
    private String userId;

    /**
     * @brief 챌린지 ID
     * 
     * 사용자가 참여 중인 챌린지의 ID로, `Challenge` 테이블을 참조합니다.
     */
    private Long challengeId;

    /**
     * @brief 과제 ID
     * 
     * 사용자가 수행 중인 챌린지 과제의 ID로, `ChallengeTask` 테이블을 참조합니다.
     */
    private Long taskId;

    /**
     * @brief 진행률
     * 
     * 사용자의 챌린지 진행 상태를 퍼센트(%)로 나타냅니다.
     */
    private Double progress;

    /**
     * @brief 챌린지 상태
     * 
     * 챌린지의 현재 상태를 나타냅니다.
     * 예: "in_progress", "completed", "failed".
     */
    private String status;

    /**
     * @brief 시작 날짜
     * 
     * 사용자가 챌린지를 시작한 날짜를 나타냅니다.
     */
    private String startDate;

    /**
     * @brief 종료 날짜
     * 
     * 사용자가 챌린지를 완료하거나 종료한 날짜를 나타냅니다.
     */
    private String endDate;

    /**
     * @brief 챌린지 기간
     * 
     * 챌린지가 지속되는 기간(일 단위)을 나타냅니다.
     */
    private Integer duration;
}
