package org.zerock.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @brief 댓글(Comment) 도메인 클래스
 * 
 * 이 클래스는 게시글에 달린 댓글 정보를 나타내며, 데이터베이스와 매핑됩니다.
 */
@Getter
@Setter
@ToString
public class Comment {

    /**
     * @brief 댓글 ID
     * 
     * 댓글을 고유하게 식별하기 위한 ID입니다.
     */
    @JsonProperty("commentId")
    private Long commentId;

    /**
     * @brief 게시글 ID
     * 
     * 댓글이 달린 게시글의 ID로, `CommunityPost` 테이블을 참조합니다.
     */
    @JsonProperty("postId")
    private Long postId;

    /**
     * @brief 사용자 ID
     * 
     * 댓글을 작성한 사용자의 ID입니다.
     */
    @JsonProperty("userId")
    private String userId;

    /**
     * @brief 댓글 내용
     * 
     * 댓글의 텍스트 내용을 나타냅니다.
     */
    @JsonProperty("commentText")
    private String commentText;

    /**
     * @brief 작성 시간
     * 
     * 댓글이 작성된 날짜 및 시간을 나타냅니다.
     */
    @JsonProperty("timestamp")
    private Timestamp timestamp;
}
