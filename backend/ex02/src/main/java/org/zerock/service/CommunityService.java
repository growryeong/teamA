package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.zerock.domain.Challenge;
import org.zerock.domain.ChallengeTask;
import org.zerock.domain.CommunityPost;
import org.zerock.domain.CommunityPostDTO;
import org.zerock.domain.UserChallenge;
import org.zerock.mapper.ChallengeMapper;
import org.zerock.mapper.CommunityMapper;
import org.zerock.mapper.UserChallengeMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityService {
    private final CommunityMapper communityMapper;
    private final ChallengeMapper challengeMapper;
    
    // 전체 게시글 조회
    public List<CommunityPost> getAllPosts() {
        try {
            return communityMapper.getCommunityPosts();
        } catch (Exception e) {
            log.error("게시글 목록 조회 중 오류 발생: ", e);
            throw new RuntimeException("게시글 목록을 가져오는데 실패했습니다.", e);
        }
    }

    public CommunityPostDTO getPostWithChallengeDTO(Long postId) {
        return communityMapper.getPostById(postId); // Mapper에서 DTO 반환
    }

    // 게시글 작성
    public void createPost(CommunityPost post) {
        try {
            communityMapper.insertPost(post);
        } catch (Exception e) {
            throw new RuntimeException("게시글 저장 중 오류 발생: " + e.getMessage(), e);
        }
    }

    public Map<String, Object> getPostWithChallenge(Long postId) {
        Map<String, Object> postDetails = communityMapper.getPostWithDetails(postId);
        if (postDetails == null) {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다. postId: " + postId);
        }
        return postDetails;
    }
    
}