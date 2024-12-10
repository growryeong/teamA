package org.zerock.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.domain.Comment;
import org.zerock.mapper.CommentMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentMapper.getCommentsByPostId(postId);
    }

    public void createComment(Comment comment) {
        commentMapper.insertComment(comment);
    }
}
