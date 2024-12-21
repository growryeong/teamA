package org.zerock.domain;

import java.time.LocalDate;
import lombok.Data;

/**
 * @brief 사용자 챌린지(UserChallenge) DTO 클래스
 * 
 * 이 클래스는 사용자와 챌린지 간의 참여 정보를 클라이언트와 교환하기 위한 DTO(Data Transfer Object)입니다.
 */
@Data
public class UserChallengeDTO {

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
     * @brief 진행률
     * 
     * 사용자의 챌린지 진행 상태를 퍼센트(%)로 나타냅니다.
     */
    private Double progress;

    /**
     * @brief 챌린지 상태
     * 
     * 챌린지의 상태를 나타냅니다.
     * 예: "in_progress", "completed", "failed".
     */
    private String status;

    /**
     * @brief 시작 날짜
     * 
     * 사용자가 챌린지를 시작한 날짜를 나타냅니다.
     */
    private LocalDate startDate;

    /**
     * @brief 종료 날짜
     * 
     * 사용자가 챌린지를 완료하거나 종료한 날짜를 나타냅니다.
     */
    private LocalDate endDate;
}
