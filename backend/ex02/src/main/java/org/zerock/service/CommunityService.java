package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.zerock.domain.CommunityPost;
import org.zerock.mapper.CommunityMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityService {
    private final CommunityMapper communityMapper;

    // 전체 게시글 조회
    public List<CommunityPost> getAllPosts() {
        try {
            return communityMapper.getCommunityPosts();
        } catch (Exception e) {
            log.error("게시글 목록 조회 중 오류 발생: ", e);
            throw new RuntimeException("게시글 목록을 가져오는데 실패했습니다.", e);
        }
    }

    // 특정 게시글 ID로 조회
    public CommunityPost getPostById(Long postId) {
        return communityMapper.getPostById(postId);
    }

    // 게시글 작성
    public void createPost(CommunityPost communityPost) {
        try {
            // 현재 시간 설정
            communityPost.setTimestamp(LocalDateTime.now());
            communityMapper.insertPost(communityPost);
        } catch (Exception e) {
            log.error("게시글 작성 중 오류 발생", e);
            throw new RuntimeException("게시글 작성에 실패했습니다.", e);
        }
    }
}