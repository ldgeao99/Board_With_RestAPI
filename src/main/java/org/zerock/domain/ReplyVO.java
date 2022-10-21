package org.zerock.domain;

import java.util.Date;
import lombok.Data;

@Data 
public class ReplyVO {
	private Long rno;			// 댓글 식별자
	private Long bno;			// 어떤 게시물의 댓글인지
	
	private String reply;		// 댓글 내용
	private String replyer;		// 댓글 작성자
	private Date replyDate;		// 댓글 작성시간
	private Date updateDate;	// 댓글 수정시간
}