package org.zerock.domain;

import lombok.Data;

/**
 * @brief 공지사항(Notice) DTO 클래스
 * 
 * 이 클래스는 공지사항 정보를 클라이언트와 교환하기 위한 DTO(Data Transfer Object)입니다.
 */
@Data
public class NoticeDTO {

    /**
     * @brief 공지사항 ID
     * 
     * 공지사항을 고유하게 식별하기 위한 ID입니다.
     */
    private Long id;

    /**
     * @brief 제목
     * 
     * 공지사항의 제목을 나타냅니다.
     */
    private String title;

    /**
     * @brief 내용
     * 
     * 공지사항의 상세 내용을 나타냅니다.
     */
    private String content;

    /**
     * @brief 작성자 ID
     * 
     * 공지사항을 작성한 사용자의 ID입니다.
     */
    private String authorId;

    /**
     * @brief 작성자 이름
     * 
     * 작성자의 이름을 나타냅니다. `Users` 테이블과 조인 시 사용됩니다.
     */
    private String author;

    /**
     * @brief 작성 시간
     * 
     * 공지사항이 작성된 날짜와 시간을 문자열 형식으로 나타냅니다.
     */
    private String timestamp;

    /**
     * @brief 객체의 문자열 표현 반환
     * 
     * @return `NoticeDTO` 객체의 주요 필드를 포함한 문자열 표현
     */
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
