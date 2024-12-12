package org.zerock.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CommunityPost {
    private Long postId;
    private String userId;
    private Long userChallengeId; // USER_CHALLENGE_ID 추가
    private String title;
    private String content;
    private int viewCount;
    private String timestamp;


    public void setCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
    }
}