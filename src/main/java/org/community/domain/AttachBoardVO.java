package org.community.domain;

import lombok.Data;

@Data
public class AttachBoardVO {

	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private Long bno;

}
