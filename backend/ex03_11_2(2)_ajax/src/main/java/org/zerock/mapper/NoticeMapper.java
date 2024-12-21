package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.Notice;

public interface NoticeMapper {
	List<Notice> getAllNotices();
	Notice getNoticeById(int id);
	void insertNotice(Notice notice);
	void updateNotice(Notice notice);
	void deleteNotice(int id);
}
