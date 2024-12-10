package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.NoticeDTO;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> getNoticeList();
    NoticeDTO getNotice(Long noticeId);
    void insertNotice(NoticeDTO notice);
}
