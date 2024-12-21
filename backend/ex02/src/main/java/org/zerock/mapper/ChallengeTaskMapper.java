package org.zerock.mapper;

import org.zerock.domain.ChallengeTask;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @brief 챌린지 과제를 처리하기 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 챌린지 과제의 조회 및 등록과 관련된 데이터베이스 작업을 처리합니다.
 */
@Mapper
public interface ChallengeTaskMapper {

    /**
     * @brief 특정 챌린지에 포함된 과제 조회
     * 
     * 주어진 챌린지 ID를 기반으로 해당 챌린지에 포함된 과제 목록을 조회합니다.
     * 
     * @param challengeId 조회할 챌린지의 ID
     * @return 해당 챌린지의 과제 리스트
     */
    List<ChallengeTask> findByChallengeId(Long challengeId);

    /**
     * @brief 모든 챌린지 과제 조회
     * 
     * 데이터베이스에 등록된 모든 챌린지 과제 정보를 조회합니다.
     * 
     * @return 모든 챌린지 과제의 리스트
     */
    List<ChallengeTask> findAllTasks();

    /**
     * @brief 새로운 챌린지 과제 삽입
     * 
     * 주어진 챌린지 과제를 데이터베이스에 삽입합니다.
     * 
     * @param task 삽입할 챌린지 과제 객체
     */
    void insert(ChallengeTask task);
}
