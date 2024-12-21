package org.zerock.domain;

import lombok.Data;

/**
 * @brief 챌린지 과제(ChallengeTask) 도메인 클래스
 * 
 * 이 클래스는 특정 챌린지에 속하는 과제(Task)를 나타내며, 데이터베이스와 매핑됩니다.
 */
@Data
public class ChallengeTask {

    /**
     * @brief 과제 ID
     * 
     * 과제를 고유하게 식별하기 위한 ID입니다.
     */
    private Long taskId;

    /**
     * @brief 챌린지 ID
     * 
     * 이 과제가 속한 챌린지의 ID로, `Challenge` 테이블을 참조합니다.
     */
    private Long challengeId;

    /**
     * @brief 과제 내용
     * 
     * 과제의 상세 내용 또는 설명을 나타냅니다.
     */
    private String task;
}
