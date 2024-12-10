package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.NoticeDTO;
import org.zerock.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController 
@RequestMapping("/notices")
@CrossOrigin(origins = "http://localhost:3000")
public class NoticeController {
   
   private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
   
   @Autowired
   private NoticeService noticeService;
   
   @GetMapping("")
   public ResponseEntity<?> getNoticeList() {
       try {
           logger.info("공지사항 목록 조회 요청");
           List<NoticeDTO> notices = noticeService.getNoticeList();
           logger.info("조회된 공지사항 수: {}", notices.size());
           return ResponseEntity.ok(notices);
       } catch (Exception e) {
           logger.error("공지사항 목록 조회 중 오류 발생", e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body("공지사항 목록을 불러오는데 실패했습니다: " + e.getMessage());
       }
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<?> getNoticeDetail(@PathVariable Long id) {
       try {
           logger.info("공지사항 상세 조회 요청 ID: {}", id);
           NoticeDTO notice = noticeService.getNotice(id);
           return ResponseEntity.ok(notice);
       } catch (Exception e) {
           logger.error("공지사항 상세 조회 중 오류 발생 ID: " + id, e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body("공지사항을 불러오는데 실패했습니다: " + e.getMessage());
       }
   }
}