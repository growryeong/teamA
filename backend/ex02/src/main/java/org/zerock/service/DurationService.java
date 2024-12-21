package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.Duration;
import org.zerock.mapper.DurationMapper;

import lombok.RequiredArgsConstructor;

/**
 * @brief 기간 관리를 위한 서비스
 * 
 * 이 서비스는 기간(Duration) 데이터를 처리하며,
 * 데이터베이스에서 전체 기간 목록을 조회합니다.
 */
@Service
@RequiredArgsConstructor
public class DurationService {

    /** 기간 데이터를 관리하는 Mapper */
    private final DurationMapper durationMapper;

    /**
     * @brief 전체 기간 목록 조회
     * 
     * 데이터베이스에 등록된 모든 기간 정보를 조회합니다.
     * 
     * @return 전체 기간의 리스트
     */
    public List<Duration> getAllDurations() {
        return durationMapper.findAll();
    }
}
