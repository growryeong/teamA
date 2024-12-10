package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.NoticeDTO;
import org.zerock.mapper.NoticeMapper;

@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    
    // 공지사항 목록 조회
    public List<NoticeDTO> getNoticeList() {
        return noticeMapper.getNoticeList();
    }
    
    // 공지사항 상세 조회
    public NoticeDTO getNotice(Long noticeId) throws Exception {
        NoticeDTO notice = noticeMapper.getNotice(noticeId);
        if (notice == null) {
            throw new Exception("해당 공지사항을 찾을 수 없습니다.");
        }
        return notice;
    }
    
    // 공지사항 등록
    public void createNotice(NoticeDTO notice) {
        noticeMapper.insertNotice(notice);
    }
}
