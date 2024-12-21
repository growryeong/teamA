package org.zerock.domain;

import lombok.Data;

/**
 * @brief 커뮤니티 게시글 상세 정보 DTO 클래스
 * 
 * 이 클래스는 커뮤니티 게시판의 게시글 및 관련 정보를 클라이언트와 교환하기 위한 DTO(Data Transfer Object)입니다.
 */
@Data
public class CommunityPostDTO {

    /**
     * @brief 게시글 ID
     * 
     * 게시글을 고유하게 식별하기 위한 ID입니다.
     */
    private Long postId;

    /**
     * @brief 작성자 ID
     * 
     * 게시글을 작성한 사용자의 ID입니다.
     */
    private String userId;

    /**
     * @brief 챌린지 ID
     * 
     * 게시글이 연결된 챌린지의 ID로, `Challenge` 테이블을 참조합니다.
     */
    private Long challengeId;

    /**
     * @brief 사용자 챌린지 ID
     * 
     * 게시글이 연결된 사용자 챌린지의 ID로, `UserChallenge` 테이블을 참조합니다.
     */
    private Long userChallengeId;

    /**
     * @brief 게시글 제목
     * 
     * 게시글의 제목을 나타냅니다.
     */
    private String title;

    /**
     * @brief 게시글 내용
     * 
     * 게시글의 상세 내용을 나타냅니다.
     */
    private String content;

    /**
     * @brief 조회수
     * 
     * 게시글이 조회된 횟수를 나타냅니다.
     */
    private int viewCount;

    /**
     * @brief 작성 시간
     * 
     * 게시글이 작성된 날짜와 시간을 문자열 형식("yyyy-MM-dd HH:mm:ss")으로 저장합니다.
     */
    private String timestamp; // TIMESTAMP를 문자열로 변환
}
