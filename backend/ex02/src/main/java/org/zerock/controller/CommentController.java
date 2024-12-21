package org.zerock.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.Comment;
import org.zerock.service.CommentService;

import java.util.List;
import java.util.Map;

/**
 * @brief 댓글 관리를 위한 REST 컨트롤러
 * 
 * 이 컨트롤러는 댓글 등록 및 조회와 관련된 API를 제공합니다.
 */
@Slf4j
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
public class CommentController {

    /** 댓글 관리를 위한 서비스 */
    private final CommentService commentService;

    /**
     * @brief 댓글 등록
     * 
     * 사용자가 댓글을 작성하면 해당 댓글을 데이터베이스에 추가합니다.
     * 
     * @param payload 댓글 작성에 필요한 정보 (postId, userId, commentText)
     * @return 성공적으로 등록되었는지 여부 메시지
     */
    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody Map<String, Object> payload) {
        try {
            // 요청 데이터에서 필요한 정보 추출
            Long postId = ((Number) payload.get("postId")).longValue();
            String userId = (String) payload.get("userId");
            String commentText = (String) payload.get("commentText");

            // 댓글 등록 서비스 호출
            commentService.addComment(postId, userId, commentText);

            // 성공 응답 반환
            return ResponseEntity.ok("댓글이 성공적으로 추가되었습니다.");
        } catch (Exception e) {
            log.error("댓글 추가 중 오류 발생: ", e);

            // 오류 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("댓글 추가 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * @brief 특정 게시글의 댓글 목록 조회
     * 
     * 주어진 게시글 ID에 해당하는 모든 댓글을 반환합니다.
     * 
     * @param postId 댓글을 조회할 게시글의 ID
     * @return 댓글 목록
     */
    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        // 댓글 목록 조회 서비스 호출
        List<Comment> comments = commentService.getCommentsByPostId(postId);

        // 조회된 댓글 확인용 로그 출력
        log.info("Fetched Comments: {}", comments);

        // 조회 결과 반환
        return ResponseEntity.ok(comments);
    }
}
