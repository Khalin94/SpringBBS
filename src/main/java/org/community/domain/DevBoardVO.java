package org.community.domain;

import java.util.Date;

import lombok.Data;

@Data
public class DevBoardVO {
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	private Long hits;

}
