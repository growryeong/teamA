package org.zerock.domain;

import lombok.Data;

/**
 * @brief 사용자(User) DTO 클래스
 * 
 * 이 클래스는 사용자 정보를 클라이언트와 교환하기 위한 DTO(Data Transfer Object)입니다.
 */
@Data
public class UserDTO {

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
     * 사용자의 이름 또는 닉네임을 나타냅니다.
     */
    private String username;

    /**
     * @brief 이메일 주소
     * 
     * 사용자의 이메일 주소를 나타냅니다.
     */
    private String email;
}
