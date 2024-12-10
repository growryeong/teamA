package org.zerock.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoticeDTO {
    private Long id;        // 공지사항 ID
    private String title;         // 제목
    private String content;       // 내용
    private String authorId;      // 작성자 ID
    private String author;    // 작성자 이름 (Users 테이블과 조인시 사용)
    private String timestamp;  // 작성시간
    
    @Override
    public String toString() {
        return "NoticeDTO{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", authorId='" + authorId + '\'' +
               ", author='" + author + '\'' +
               ", timestamp='" + timestamp + '\'' +
               '}';
    }

}
