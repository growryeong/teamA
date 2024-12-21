package org.zerock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.domain.Comment;
import org.zerock.mapper.CommentMapper;

import java.util.List;

/**
 * @brief 댓글 관리를 위한 서비스
 * 
 * 이 서비스는 댓글 등록 및 조회와 관련된 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
public class CommentService {

    /** 댓글 관리를 위한 Mapper */
    private final CommentMapper commentMapper;

    /**
     * @brief 댓글 등록
     * 
     * 주어진 게시글 ID와 사용자 ID, 댓글 내용을 기반으로 새로운 댓글을 등록합니다.
     * 
     * @param postId 댓글이 등록될 게시글의 ID
     * @param userId 댓글을 작성한 사용자의 ID
     * @param commentText 댓글 내용
     */
    public void addComment(Long postId, String userId, String commentText) {
        commentMapper.insertComment(postId, userId, commentText);
    }

    /**
     * @brief 게시글 ID를 기반으로 댓글 조회
     * 
     * 특정 게시글에 등록된 모든 댓글을 조회합니다.
     * 
     * @param postId 댓글을 조회할 게시글의 ID
     * @return 게시글에 등록된 댓글 리스트
     */
    public List<Comment> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentMapper.getCommentsByPostId(postId);
        return comments;
    }
}
