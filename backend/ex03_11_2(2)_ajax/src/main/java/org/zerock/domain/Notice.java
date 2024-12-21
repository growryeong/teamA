package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Notice {
	private int id;
	private String title;
	private String content;
	private String author;
	private Date timesetamp;
}
