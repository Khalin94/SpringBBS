package org.community.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	private Long hits;
	
	private int replyCnt;

	private List<AttachBoardVO> attachList;
}
