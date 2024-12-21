package org.zerock.mapper;

import org.zerock.domain.Challenge;
import org.zerock.domain.ChallengeTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @brief 챌린지 데이터를 처리하기 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 챌린지와 관련된 데이터베이스 작업을 처리하며,
 * 챌린지 목록 조회, 삽입, 검색 등의 기능을 제공합니다.
 */
@Mapper
public interface ChallengeMapper {

    /**
     * @brief 모든 챌린지 조회
     * 
     * 데이터베이스에 등록된 모든 챌린지 정보를 조회합니다.
     * 
     * @return 모든 챌린지의 리스트
     */
    List<Challenge> findAll();

    /**
     * @brief 새로운 챌린지 삽입
     * 
     * 주어진 챌린지 객체를 데이터베이스에 삽입합니다.
     * 
     * @param challenge 삽입할 챌린지 객체
     */
    void insert(Challenge challenge);

    /**
     * @brief 챌린지 ID로 챌린지 조회
     * 
     * 특정 챌린지 ID를 기반으로 챌린지 정보를 조회합니다.
     * 
     * @param challengeId 조회할 챌린지의 ID
     * @return 해당 챌린지 객체
     */
    Challenge findById(Long challengeId);

    /**
     * @brief 챌린지 제목과 유형으로 챌린지 조회
     * 
     * 제목과 유형 이름을 기반으로 챌린지 정보를 조회합니다.
     * 
     * @param title 챌린지 제목
     * @param typeName 챌린지 유형 이름
     * @return 해당 챌린지 객체
     */
    Challenge findByTitleAndType(@Param("title") String title, @Param("typeName") String typeName);

    /**
     * @brief 챌린지 제목과 유형으로 챌린지 조회 (Map 형태의 매개변수 사용)
     * 
     * 제목과 유형 이름을 Map으로 전달받아 챌린지 정보를 조회합니다.
     * 
     * @param params 제목과 유형 이름을 포함한 매개변수 Map
     * @return 해당 챌린지 객체
     */
    Challenge findByTitleAndType(Map<String, Object> params);

    /**
     * @brief 챌린지 ID로 챌린지 조회
     * 
     * 챌린지 ID를 기반으로 챌린지 정보를 조회합니다.
     * 
     * @param challengeId 조회할 챌린지의 ID
     * @return 해당 챌린지 객체
     */
    Challenge findByChallengeId(@Param("challengeId") Long challengeId);

    /**
     * @brief 챌린지 ID로 챌린지 과제 조회
     * 
     * 특정 챌린지 ID에 해당하는 과제 정보를 조회합니다.
     * 
     * @param challengeId 조회할 챌린지의 ID
     * @return 해당 챌린지의 과제 정보
     */
    ChallengeTask findTaskByChallengeId(@Param("challengeId") Long challengeId);
}
