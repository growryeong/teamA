package org.zerock.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @brief 커뮤니티 게시글(CommunityPost) 도메인 클래스
 * 
 * 이 클래스는 커뮤니티 게시판의 게시글 정보를 나타내며, 데이터베이스와 매핑됩니다.
 */
@Data
public class CommunityPost {

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
     * 게시글이 작성된 날짜와 시간을 나타냅니다.
     * 시간은 "yyyy-MM-dd HH:mm:ss" 형식으로 저장됩니다.
     */
    private String timestamp;

    /**
     * @brief 현재 시간 설정
     * 
     * 게시글 작성 시간을 현재 시간으로 설정합니다.
     * 시간 형식은 "yyyy-MM-dd HH:mm:ss"입니다.
     */
    public void setCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
    }
}
