package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.Comment;
import org.zerock.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
        return ResponseEntity.ok("댓글이 성공적으로 등록되었습니다.");
    }
}
