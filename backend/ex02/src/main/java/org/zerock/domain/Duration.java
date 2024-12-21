package org.zerock.domain;

import lombok.Data;

/**
 * @brief 챌린지 기간(Duration) 도메인 클래스
 * 
 * 이 클래스는 챌린지 진행 기간에 대한 정보를 나타내며, 데이터베이스와 매핑됩니다.
 */
@Data
public class Duration {

    /**
     * @brief 기간 ID
     * 
     * 챌린지 기간을 고유하게 식별하기 위한 ID입니다.
     */
    private Long durationId;

    /**
     * @brief 기간 값
     * 
     * 챌린지의 기간(일 단위)을 나타냅니다.
     */
    private Integer value;
}
