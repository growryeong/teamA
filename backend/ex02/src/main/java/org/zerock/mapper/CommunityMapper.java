package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.CommunityPost;
import org.zerock.domain.CommunityPostDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {
    // 전체 게시글 조회
    List<CommunityPost> getCommunityPosts();
    
    // 특정 게시글 조회 (단일 결과 반환으로 변경)
    CommunityPostDTO getPostById(Long postId);
    
    // 게시글 삽입
    void insertPost(CommunityPost post);

    // 특정 게시글 상세 정보 가져오기
    Map<String, Object> getPostWithDetails(Long postId);
    
    // 조회수 증가
    void updateViewCount(Long postId);
    
    // 게시글과 챌린지 정보 함께 조회
    Map<String, Object> getPostWithChallenge(@Param("postId") Long postId);


    // 게시글 수정
    void updatePost(CommunityPost communityPost);

    // 게시글 삭제
    void deletePost(@Param("postId") Long postId);
}