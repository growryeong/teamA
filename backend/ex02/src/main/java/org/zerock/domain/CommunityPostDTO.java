package org.zerock.domain;

import lombok.Data;

@Data
public class CommunityPostDTO {
    private Long postId;
    private String userId;
    private Long challengeId;
    private Long userChallengeId;
    private String title;
    private String content;
    private int viewCount;
    private String timestamp; // TIMESTAMP를 문자열로 변환

}
