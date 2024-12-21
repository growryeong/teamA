package org.zerock.mapper;

import org.zerock.domain.Duration;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @brief 기간(Duration) 데이터를 처리하기 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 기간 데이터를 조회하는 데이터베이스 작업을 처리합니다.
 */
@Mapper
public interface DurationMapper {

    /**
     * @brief 전체 기간 목록 조회
     * 
     * 데이터베이스에 등록된 모든 기간 정보를 조회합니다.
     * 
     * @return 모든 기간의 리스트
     */
    List<Duration> findAll();
}
