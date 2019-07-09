package org.community.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class FreeBoardVO {
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	private Long hits;
	
	private int replyCnt;
	
	private List<FreeBoardAttachVO> attachList;
	
	public String voPrint() {
		return "bno : " + bno + "title : " + title + "content : " + content + "writer : " + writer + 
				"regDate : " + regDate + "updateDate : " + updateDate + "hits : "+ hits + "attachList : " + attachList;
	}

}
