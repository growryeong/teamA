package org.zerock.domain;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @brief 사용자(User) 도메인 클래스
 * 
 * 이 클래스는 사용자의 기본 정보를 나타내며, 데이터베이스와 매핑됩니다.
 */
@Data
public class User {

    /**
     * @brief 사용자 ID
     * 
     * 사용자를 고유하게 식별하기 위한 ID입니다.
     */
    private String userId;

    /**
     * @brief 비밀번호
     * 
     * 사용자의 계정 비밀번호를 나타냅니다.
     */
    private String password;

    /**
     * @brief 사용자 이름
     * 
     * 사용자의 이름 또는 별명을 나타냅니다.
     */
    private String username;

    /**
     * @brief 계정 생성 날짜
     * 
     * 사용자의 계정이 생성된 날짜와 시간을 나타냅니다.
     */
    private LocalDateTime createdAt;

    /**
     * @brief 이메일 주소
     * 
     * 사용자의 이메일 주소를 나타냅니다.
     */
    private String email;

    /**
     * @brief 관리자 여부
     * 
     * 사용자가 관리자 권한을 가지고 있는지 나타냅니다. 
     * (1: 관리자, 0: 일반 사용자)
     */
    private Integer isAdmin;
}
