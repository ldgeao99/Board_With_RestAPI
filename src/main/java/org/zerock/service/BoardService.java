package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	
	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	//public List<BoardVO> getList(); 모든 게시물 레코드를 내어주던 메소드
	
	public List<BoardVO> getList(Criteria cri); // N 페이지에서 보여줄 K개의 게시물만 가져오는 메소드 
	
	public int getTotal(Criteria cri);
}
