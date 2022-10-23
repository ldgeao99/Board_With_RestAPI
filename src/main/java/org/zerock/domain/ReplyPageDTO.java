package org.zerock.domain;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
	
	private int replyCnt;			// 특정 게시물의 총 댓글 수
	private List<ReplyVO> list;		// 특정 게시물의 댓글들
	
}
