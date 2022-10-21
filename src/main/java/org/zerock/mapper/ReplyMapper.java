package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	public int insert(ReplyVO vo); 				// 특정 게시물에 댓글 추가
	
	public ReplyVO read(Long rno);	 			// 특정 댓글 읽기
	
	public int delete(Long rno);				// 특정 댓글 삭제하기
	
	public int update(ReplyVO vo);				// 댓글 수정하기
	
	public List<ReplyVO> getListWithPaging( 	// 페이지별 댓글들을 가져오는 메소드
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	// java에서 MyBatis xml 쪽으로 두 개 이상의 데이터를 파라미터로 전달하기 위해서는 
	// 1) 별도의 객체로 구성하거나
	// 2) Map을 이용하는 방식 
	// 3) @Param을 이용해서 이름을 사용하는 방식 => 위에서 사용한 방식
}
