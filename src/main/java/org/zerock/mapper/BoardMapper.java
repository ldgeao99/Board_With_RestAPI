package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

//이 인터페이스는 root-context.xml에서 mybatis-spring 으로 스캔해줌
public interface BoardMapper {
	
	//@Select("select * from tbl_board where bno > 0") 이거 다시 살리면 xml 방식하고 섞어서 사용가능
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno); 
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
}
