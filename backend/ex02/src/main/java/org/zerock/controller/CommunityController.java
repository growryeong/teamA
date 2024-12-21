package org.zerock.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.CommunityPost;
import org.zerock.domain.CommunityPostDTO;
import org.zerock.mapper.UserChallengeMapper;
import org.zerock.service.CommunityService;

import java.util.List;

/**
 * @brief 커뮤니티 게시글 관리를 위한 REST 컨트롤러
 * 
 * 이 컨트롤러는 커뮤니티 게시글의 CRUD 작업을 처리하기 위한 API를 제공합니다.
 */
@Slf4j
@RestController
@RequestMapping("/api/communityPosts")
@CrossOrigin(origins = "http://localhost:3000") // CORS 허용
@RequiredArgsConstructor
public class CommunityController {

    /** 커뮤니티 게시글 관리를 위한 서비스 */
    private final CommunityService communityService;
    /** 사용자 챌린지 매핑 관리를 위한 매퍼 */
    private final UserChallengeMapper userChallengeMapper;

    /**
     * @brief 모든 게시글 조회
     * 
     * 커뮤니티에 등록된 모든 게시글을 조회합니다.
     * 
     * @return 게시글 리스트를 포함한 ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<CommunityPost>> getPosts() {
        try {
            // 게시글 조회 서비스 호출
            List<CommunityPost> posts = communityService.getAllPosts();
            log.info("조회된 게시글 수: {}", posts.size());
            log.info("게시글 데이터: {}", posts);

            // 성공적으로 조회된 게시글 리스트 반환
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            log.error("게시글 조회 중 오류 발생: ", e);

            // 오류 발생 시 500 응답 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * @brief 특정 게시글 및 연관된 챌린지 정보 조회
     * 
     * 주어진 게시글 ID를 기반으로 게시글과 연관된 챌린지 정보를 조회합니다.
     * 
     * @param postId 조회할 게시글의 ID
     * @return 게시글 및 챌린지 정보를 포함한 DTO
     */
    @GetMapping("/{postId}")
    public ResponseEntity<CommunityPostDTO> getPostWithChallenge(@PathVariable Long postId) {
        // DTO를 통해 게시글과 관련된 정보를 조회
        CommunityPostDTO postDTO = communityService.getPostWithChallengeDTO(postId);

        // 조회 결과 반환
        return ResponseEntity.ok(postDTO);
    }

    /**
     * @brief 새로운 게시글 생성
     * 
     * 사용자가 작성한 게시글 정보를 받아 새로운 게시글을 등록합니다.
     * 
     * @param postRequest 게시글 생성을 위한 요청 데이터
     * @return 성공 또는 실패 메시지
     */
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody CommunityPostRequest postRequest) {
        try {
            // 게시글 객체 생성 및 데이터 설정
            CommunityPost post = new CommunityPost();
            post.setUserId(postRequest.getUserId());
            post.setUserChallengeId(postRequest.getUserChallengeId());
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setViewCount(postRequest.getViewCount());

            // 게시글 등록 서비스 호출
            communityService.createPost(post);

            // 성공 메시지 반환
            return ResponseEntity.ok("게시글이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            log.error("게시글 등록 중 오류 발생: ", e);

            // 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("게시글 등록 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * @brief 게시글 생성을 위한 요청 객체
     * 
     * 이 클래스는 게시글 생성을 위해 필요한 데이터를 캡슐화합니다.
     */
    @Getter @Setter
    public static class CommunityPostRequest {
        /** 게시글 작성자의 사용자 ID */
        private String userId;
        /** 게시글과 연결된 사용자 챌린지 ID */
        private Long userChallengeId; // USER_CHALLENGE_ID 추가
        /** 게시글 제목 */
        private String title;
        /** 게시글 내용 */
        private String content;
        /** 게시글 조회수 */
        private int viewCount;
    }
}
