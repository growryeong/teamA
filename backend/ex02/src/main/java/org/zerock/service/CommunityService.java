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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @brief 커뮤니티 게시글 관리를 위한 서비스
 * 
 * 이 서비스는 커뮤니티 게시글의 CRUD 작업과 관련된 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityService {

    /** 커뮤니티 게시글 관리를 위한 Mapper */
    private final CommunityMapper communityMapper;

    /** 챌린지 관리를 위한 Mapper */
    private final ChallengeMapper challengeMapper;

    /**
     * @brief 전체 게시글 조회
     * 
     * 커뮤니티에 등록된 모든 게시글을 조회합니다.
     * 
     * @return 모든 게시글 리스트
     * @throws RuntimeException 게시글 조회 중 오류 발생 시
     */
    public List<CommunityPost> getAllPosts() {
        try {
            return communityMapper.getCommunityPosts();
        } catch (Exception e) {
            log.error("게시글 목록 조회 중 오류 발생: ", e);
            throw new RuntimeException("게시글 목록을 가져오는데 실패했습니다.", e);
        }
    }

    /**
     * @brief 게시글 상세 정보 및 관련 챌린지 조회
     * 
     * 특정 게시글 ID를 기반으로 게시글 및 관련 챌린지 정보를 조회합니다.
     * 
     * @param postId 조회할 게시글의 ID
     * @return 게시글 및 관련 챌린지 정보를 포함한 DTO
     */
    public CommunityPostDTO getPostWithChallengeDTO(Long postId) {
        return communityMapper.getPostById(postId); // Mapper에서 DTO 반환
    }

    /**
     * @brief 새로운 게시글 작성
     * 
     * 사용자가 작성한 게시글을 데이터베이스에 저장합니다.
     * 
     * @param post 저장할 게시글 객체
     * @throws RuntimeException 게시글 저장 중 오류 발생 시
     */
    public void createPost(CommunityPost post) {
        try {
            communityMapper.insertPost(post);
        } catch (Exception e) {
            throw new RuntimeException("게시글 저장 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * @brief 게시글 및 챌린지 상세 정보 조회
     * 
     * 특정 게시글 ID를 기반으로 게시글과 관련된 상세 정보를 조회합니다.
     * 
     * @param postId 조회할 게시글의 ID
     * @return 게시글 및 관련 상세 정보가 포함된 Map
     * @throws RuntimeException 게시글이 존재하지 않을 경우
     */
    public Map<String, Object> getPostWithChallenge(Long postId) {
        Map<String, Object> postDetails = communityMapper.getPostWithDetails(postId);
        if (postDetails == null) {
            throw new RuntimeException("해당 게시글을 찾을 수 없습니다. postId: " + postId);
        }
        return postDetails;
    }

    /**
     * @brief 필터 조건에 따른 게시글 조회
     * 
     * 챌린지 유형과 기간을 기준으로 필터링된 게시글 목록을 조회합니다.
     * 
     * @param challengeType 챌린지 유형
     * @param duration 챌린지 기간
     * @return 필터링된 게시글 목록
     */
    public List<Map<String, Object>> getFilteredCommunity(String challengeType, Integer duration) {
        return communityMapper.fetchFilteredCommunity(challengeType, duration);
    }
}
