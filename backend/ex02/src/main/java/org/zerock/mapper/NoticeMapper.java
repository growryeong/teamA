package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.NoticeDTO;

/**
 * @brief 공지사항 관리를 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 공지사항의 목록 조회, 상세 조회, 등록과 관련된
 * 데이터베이스 작업을 처리합니다.
 */
@Mapper
public interface NoticeMapper {

    /**
     * @brief 공지사항 목록 조회
     * 
     * 데이터베이스에 등록된 모든 공지사항 목록을 조회합니다.
     * 
     * @return 공지사항 리스트
     */
    List<NoticeDTO> getNoticeList();

    /**
     * @brief 특정 공지사항 조회
     * 
     * 주어진 공지사항 ID를 기반으로 공지사항 상세 정보를 조회합니다.
     * 
     * @param noticeId 조회할 공지사항의 ID
     * @return 공지사항 상세 정보 DTO
     */
    NoticeDTO getNotice(Long noticeId);

    /**
     * @brief 새로운 공지사항 등록
     * 
     * 주어진 공지사항 객체를 데이터베이스에 삽입합니다.
     * 
     * @param notice 등록할 공지사항 객체
     */
    void insertNotice(NoticeDTO notice);
}
