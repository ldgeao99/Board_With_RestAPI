package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;	// 보여줄 페이지번호
	private int amount;		// 한 페이지에서 보여줄 게시물의 개수
	
	public Criteria() {
		this(1,10); 		// 기본은 1페이지, 10개씩
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
