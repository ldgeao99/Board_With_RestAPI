package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;

/* 계산결과를 model 에 붙여서 보낸다음 jsp에서 그려내는 방식을 사용할때 사용하게 되는 클래스로 ajax 방식에서는 필요X */
@Getter
@Setter
public class PageDTO {
	
	private int startPage; 
	private int endPage; 
	private boolean prev, next;
	
	private int total;
	private Criteria cri;		
	
	/* 선택된 페이지번호에 따라 어떤 번호 및 어떤 버튼을 보여줘야하는지에 대한 정보를 담고있는 객체로, 이후 컨트롤러에서 Model에 이 객체를 담아서 화면에 전달함 */
	public PageDTO(Criteria cri, int total) {
		// 보여줄 페이지번호, 한 페이지에서 보여줄 레코드의 개수 정보를 담고있음
		this.cri = cri;	

		// 전체 레코드의 개수
		this.total = total;		
		
		// ex) cri.pageNum에 담겨있는 페이지 번호가 12이면 보여지는 페이지 번호 들을 생각해볼 때, startPage=11, endPage=20 이다. 
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10; // 페이지번호 표시할때 맨 오른쪽에 표시될 숫자 
		this.startPage = this.endPage - 9;								// 페이지번호 표시할때 맨 왼쪽에 표시될 숫자
		
		// 실제 총 레코드 개수를 가지고 계산한 맨 끝의 페이지번호 
		int realEndPage = (int) ( Math.ceil(total * 1.0) / cri.getAmount());

		// ex) realEndPage가 13이라면 1~10 에서는 그대로 1~10을 보여주면 되지만, 11~20 에서는 20대신 '13'을 보여줘야 하므로  
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		// 상황에 따라 이전, 다음 버튼 보여줄지 여부
		this.prev = this.startPage > 1; 
		this.next = this.endPage < realEndPage;
	}
	
	
}
