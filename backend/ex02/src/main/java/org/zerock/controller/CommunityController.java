package org.zerock.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.CommunityPost;
import org.zerock.domain.CommunityPostDTO;
import org.zerock.domain.UserChallenge;
import org.zerock.mapper.UserChallengeMapper;
import org.zerock.service.CommunityService;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/communityPosts")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CommunityController {
    private final CommunityService communityService;
    private final UserChallengeMapper userChallengeMapper;


    // �쟾泥� 寃뚯떆湲� 議고쉶
    @GetMapping
    public ResponseEntity<List<CommunityPost>> getPosts() {
        try {
            List<CommunityPost> posts = communityService.getAllPosts();
            log.info("議고쉶�맂 寃뚯떆湲� �닔: {}", posts.size());
            log.info("寃뚯떆湲� �뜲�씠�꽣: {}", posts);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            log.error("寃뚯떆湲� 議고쉶 以� �삤瑜� 諛쒖깮: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CommunityPostDTO> getPostWithChallenge(@PathVariable Long postId) {
        CommunityPostDTO postDTO = communityService.getPostWithChallengeDTO(postId);
        return ResponseEntity.ok(postDTO);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody CommunityPostRequest postRequest) {
        try {
            CommunityPost post = new CommunityPost();
            post.setUserId(postRequest.getUserId());
            post.setUserChallengeId(postRequest.getUserChallengeId());
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setViewCount(postRequest.getViewCount());

            communityService.createPost(post);

            return ResponseEntity.ok("게시글이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("게시글 등록 중 오류 발생: " + e.getMessage());
        }
    }

    @Getter @Setter
    public static class CommunityPostRequest {
        private String userId;
        private Long userChallengeId; // USER_CHALLENGE_ID 추가
        private String title;
        private String content;
        private int viewCount;
    }
}
