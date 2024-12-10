package org.zerock.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Comment;

@Mapper
public interface CommentMapper {
	List<Comment> getCommentsByPostId(Long postId);
	
	void insertComment(Comment comment);

}
