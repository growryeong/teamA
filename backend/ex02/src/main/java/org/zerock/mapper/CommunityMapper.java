package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.CommunityPost;

import java.util.List;

@Mapper
public interface CommunityMapper {
    // 전체 게시글 조회
    List<CommunityPost> getCommunityPosts();
    
    // 특정 게시글 조회 (단일 결과 반환으로 변경)
    CommunityPost getPostById(Long postId);
    
    // 게시글 작성
    void insertPost(CommunityPost communityPost);
    
    // 조회수 증가
    void updateViewCount(Long postId);
}