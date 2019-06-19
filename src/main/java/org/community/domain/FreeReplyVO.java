package org.community.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FreeReplyVO {
	
	private Long rno;
	private Long bno;
	
	private String reply;
	private String replyer;
	private Date ReplyDate;
	private Date updateDate;

}
