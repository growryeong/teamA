package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Notice;
import org.zerock.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	public List<Notice> getAllNotices() {
		return noticeMapper.getAllNotices();
	}
	
	public Notice getNoticeById(int id) {
		return noticeMapper.getNoticeById(id);
	}
	
	public void insertNotice(Notice notice) {
		noticeMapper.insertNotice(notice);
	}
	
	public void updateNotice(Notice notice) {
		noticeMapper.updateNotice(notice);
	}
	
	public void deleteNotice(int id) {
		noticeMapper.deleteNotice(id);
	}
}
