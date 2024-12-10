package org.zerock.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CommunityPost {
    private Long postId;
    private String userId;
    private String title;
    private String content;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime timestamp;

    // timestamp getter/setter 수정
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        if (timestamp != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return timestamp.format(formatter);
        }
        return null;
    }
}