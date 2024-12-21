package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.NoticeDTO;
import org.zerock.mapper.NoticeMapper;

/**
 * @brief 공지사항 관리를 위한 서비스
 * 
 * 이 서비스는 공지사항의 목록 조회, 상세 조회, 등록과 관련된
 * 비즈니스 로직을 처리합니다.
 */
@Service
public class NoticeService {

    /** 공지사항 데이터를 관리하는 Mapper */
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * @brief 공지사항 목록 조회
     * 
     * 데이터베이스에 등록된 모든 공지사항 목록을 조회합니다.
     * 
     * @return 공지사항 리스트
     */
    public List<NoticeDTO> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

    /**
     * @brief 공지사항 상세 조회
     * 
     * 특정 공지사항 ID를 기반으로 상세 정보를 조회합니다.
     * 
     * @param noticeId 조회할 공지사항의 ID
     * @return 공지사항 상세 정보
     * @throws Exception 공지사항이 존재하지 않을 경우
     */
    public NoticeDTO getNotice(Long noticeId) throws Exception {
        NoticeDTO notice = noticeMapper.getNotice(noticeId);
        if (notice == null) {
            throw new Exception("해당 공지사항을 찾을 수 없습니다.");
        }
        return notice;
    }

    /**
     * @brief 공지사항 등록
     * 
     * 새로운 공지사항을 데이터베이스에 등록합니다.
     * 
     * @param notice 등록할 공지사항 데이터
     */
    public void createNotice(NoticeDTO notice) {
        noticeMapper.insertNotice(notice);
    }
}
