package org.community.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String username;
	private String password;
	private String name;
	private String enabled;
	
	private Date regDate;

	private List<AuthVO> authList;

}
