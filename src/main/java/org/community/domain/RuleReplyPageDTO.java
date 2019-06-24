package org.community.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleReplyPageDTO {
	
	private int replyCnt;
	private List<RuleReplyVO> list;

}
