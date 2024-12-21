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

/**
 * @brief 공지사항 관리를 위한 REST 컨트롤러
 * 
 * 이 컨트롤러는 공지사항의 목록 조회와 상세 조회를 처리하기 위한 API를 제공합니다.
 */
@RestController
@RequestMapping("/notices")
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class NoticeController {

    /** 로깅을 위한 Logger 인스턴스 */
    private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    /** 공지사항 관리를 위한 서비스 */
    @Autowired
    private NoticeService noticeService;

    /**
     * @brief 공지사항 목록 조회
     * 
     * 등록된 모든 공지사항의 목록을 조회합니다.
     * 
     * @return 공지사항 목록을 포함한 ResponseEntity
     */
    @GetMapping("")
    public ResponseEntity<?> getNoticeList() {
        try {
            logger.info("공지사항 목록 조회 요청");

            // 공지사항 목록 조회 서비스 호출
            List<NoticeDTO> notices = noticeService.getNoticeList();
            logger.info("조회된 공지사항 수: {}", notices.size());

            // 공지사항 목록 반환
            return ResponseEntity.ok(notices);
        } catch (Exception e) {
            logger.error("공지사항 목록 조회 중 오류 발생", e);

            // 오류 발생 시 500 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("공지사항 목록을 불러오는데 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * @brief 공지사항 상세 조회
     * 
     * 특정 ID에 해당하는 공지사항의 상세 정보를 조회합니다.
     * 
     * @param id 조회할 공지사항의 ID
     * @return 공지사항 상세 정보를 포함한 ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoticeDetail(@PathVariable Long id) {
        try {
            logger.info("공지사항 상세 조회 요청 ID: {}", id);

            // 공지사항 상세 조회 서비스 호출
            NoticeDTO notice = noticeService.getNotice(id);

            // 조회된 공지사항 반환
            return ResponseEntity.ok(notice);
        } catch (Exception e) {
            logger.error("공지사항 상세 조회 중 오류 발생 ID: " + id, e);

            // 오류 발생 시 500 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("공지사항을 불러오는데 실패했습니다: " + e.getMessage());
        }
    }
}
