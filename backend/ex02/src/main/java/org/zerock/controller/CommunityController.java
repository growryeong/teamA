package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.CommunityPost;
import org.zerock.service.CommunityService;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/communityPosts")
@CrossOrigin(origins = "http://localhost:3000")
public class CommunityController {
    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    // 전체 게시글 조회
    @GetMapping
    public ResponseEntity<List<CommunityPost>> getPosts() {
        try {
            List<CommunityPost> posts = communityService.getAllPosts();
            log.info("조회된 게시글 수: {}", posts.size());
            log.info("게시글 데이터: {}", posts);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            log.error("게시글 조회 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 특정 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<CommunityPost> getPostById(@PathVariable Long postId) {
        try {
            CommunityPost post = communityService.getPostById(postId);
            if (post == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            log.error("게시글 조회 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 게시글 작성
    @PostMapping
    public ResponseEntity<Map<String, String>> createPost(@RequestBody CommunityPost communityPost) {
        try {
            communityService.createPost(communityPost);
            Map<String, String> response = new HashMap<>();
            response.put("message", "게시글이 성공적으로 등록되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("게시글 등록 중 오류 발생: ", e);
            Map<String, String> response = new HashMap<>();
            response.put("error", "게시글 등록에 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
