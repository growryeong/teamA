package org.zerock.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Comment {
	private Long postId;
	private String userId;
	private String commentText;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate timestamp;
}
