package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.CommunityPost;
import org.zerock.domain.CommunityPostDTO;

import java.util.List;
import java.util.Map;

/**
 * @brief 커뮤니티 게시글 관리를 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 커뮤니티 게시글의 조회, 삽입, 수정, 삭제와
 * 관련된 데이터베이스 작업을 처리합니다.
 */
@Mapper
public interface CommunityMapper {

    /**
     * @brief 전체 게시글 조회
     * 
     * 데이터베이스에 등록된 모든 커뮤니티 게시글을 조회합니다.
     * 
     * @return 모든 커뮤니티 게시글 리스트
     */
    List<CommunityPost> getCommunityPosts();

    /**
     * @brief 특정 게시글 조회
     * 
     * 게시글 ID를 기반으로 단일 게시글의 상세 정보를 조회합니다.
     * 
     * @param postId 조회할 게시글의 ID
     * @return 게시글 상세 정보 DTO
     */
    CommunityPostDTO getPostById(Long postId);

    /**
     * @brief 새로운 게시글 삽입
     * 
     * 주어진 커뮤니티 게시글 객체를 데이터베이스에 삽입합니다.
     * 
     * @param post 삽입할 게시글 객체
     */
    void insertPost(CommunityPost post);

    /**
     * @brief 특정 게시글의 상세 정보 조회
     * 
     * 주어진 게시글 ID를 기반으로 게시글의 상세 정보를 Map 형태로 조회합니다.
     * 
     * @param postId 조회할 게시글의 ID
     * @return 게시글 상세 정보 Map
     */
    Map<String, Object> getPostWithDetails(Long postId);

    /**
     * @brief 조회수 증가
     * 
     * 특정 게시글의 조회수를 증가시킵니다.
     * 
     * @param postId 조회수를 증가시킬 게시글의 ID
     */
    void updateViewCount(Long postId);

    /**
     * @brief 게시글과 관련된 챌린지 정보 조회
     * 
     * 주어진 게시글 ID를 기반으로 게시글과 관련된 챌린지 정보를 조회합니다.
     * 
     * @param postId 조회할 게시글의 ID
     * @return 게시글 및 챌린지 정보 Map
     */
    Map<String, Object> getPostWithChallenge(@Param("postId") Long postId);

    /**
     * @brief 게시글 수정
     * 
     * 주어진 커뮤니티 게시글 객체의 데이터를 수정합니다.
     * 
     * @param communityPost 수정할 게시글 객체
     */
    void updatePost(CommunityPost communityPost);

    /**
     * @brief 게시글 삭제
     * 
     * 주어진 게시글 ID를 기반으로 게시글을 삭제합니다.
     * 
     * @param postId 삭제할 게시글의 ID
     */
    void deletePost(@Param("postId") Long postId);

    /**
     * @brief 필터 조건에 따른 게시글 조회
     * 
     * 챌린지 유형과 기간을 기반으로 필터링된 커뮤니티 게시글을 조회합니다.
     * 
     * @param challengeType 챌린지 유형
     * @param duration 챌린지 기간
     * @return 필터링된 게시글 리스트
     */
    List<Map<String, Object>> fetchFilteredCommunity(
            @Param("challengeType") String challengeType,
            @Param("duration") Integer duration);
}
