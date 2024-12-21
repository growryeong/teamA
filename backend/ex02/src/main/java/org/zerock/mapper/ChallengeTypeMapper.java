package org.zerock.mapper;

import org.zerock.domain.ChallengeType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @brief 챌린지 유형 관리를 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 챌린지 유형 데이터를 조회하는 데이터베이스 작업을 처리합니다.
 */
@Mapper
public interface ChallengeTypeMapper {

    /**
     * @brief 챌린지 유형 이름으로 조회
     * 
     * 주어진 이름을 기반으로 챌린지 유형 정보를 조회합니다.
     * 
     * @param name 조회할 챌린지 유형의 이름
     * @return 해당 챌린지 유형 객체
     */
    ChallengeType findByName(@Param("name") String name);
}
