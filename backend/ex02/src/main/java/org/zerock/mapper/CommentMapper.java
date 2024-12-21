package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Comment;

/**
 * @brief 댓글 관리를 위한 Mapper 인터페이스
 * 
 * 이 인터페이스는 댓글 데이터의 등록 및 조회와 관련된 데이터베이스 작업을 처리합니다.
 */
@Mapper
public interface CommentMapper {

    /**
     * @brief 새로운 댓글 등록
     * 
     * 주어진 게시글 ID와 사용자 ID, 댓글 내용을 데이터베이스에 삽입합니다.
     * 
     * @param postId 댓글이 등록될 게시글의 ID
     * @param userId 댓글을 작성한 사용자의 ID
     * @param commentText 댓글 내용
     */
    void insertComment(@Param("postId") Long postId,
                       @Param("userId") String userId,
                       @Param("commentText") String commentText);

    /**
     * @brief 특정 게시글의 댓글 조회
     * 
     * 주어진 게시글 ID에 등록된 모든 댓글을 조회합니다.
     * 
     * @param postId 댓글을 조회할 게시글의 ID
     * @return 해당 게시글의 댓글 리스트
     */
    List<Comment> getCommentsByPostId(Long postId);
}
