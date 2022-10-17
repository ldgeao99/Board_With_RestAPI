package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

//이 인터페이스는 root-context.xml에서 mybatis-spring 으로 스캔해줌
public interface BoardMapper {
	
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno); 
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
}
