package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

//이 인터페이스는 root-context.xml에서 mybatis-spring 으로 스캔해줌
public interface BoardMapper {
	
	//@Select("select * from tbl_board where bno > 0") 이거 다시 살리면 xml 방식하고 섞어서 사용가능
	public List<BoardVO> getList();							// 모든 레코드를 전부 가져오는 메소드
	
	public List<BoardVO> getListWithPaging(Criteria cri);	// 해당 페이지에서 보여줄 10개의 레코드만 가져오는 메소드
	
	public void insert(BoardVO board);			// 게시글 추가
	
	public void insertSelectKey(BoardVO board);	// 게시글 추가, 처리후 사용된 시퀀스 번호를 알고싶을 때 
	
	public BoardVO read(Long bno); 				// 게시물 조회
	
	public int delete(Long bno);				// 게시물 삭제
	
	public int update(BoardVO board);			// 게시물 수정
	
	public int getTotalCount(Criteria cri);		// 게시물의 총 개수
}
