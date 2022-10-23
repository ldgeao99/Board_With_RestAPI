package org.zerock.contoller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController					// 뷰를 반환해주는 컨트롤러가 아님을 구분
@Log4j
@AllArgsConstructor
public class ReplyController {

	private ReplyService service; // lombok의 @AllArgsConstructor 을 통한 자동주입(인터페이스 타입으로 선언했다는 점에 주목)
	
	// consumes : json타입의 정보를 받아 처리하겠다는 의미
	@PostMapping(value="/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})	// 응답의 결과로 문자열을 반환하겠다는 의미
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){ // @RequestBody 는 요청으로 들어온 json 문자열을 해석해서 java의 객체로 변환하는 용도 
		// ResponseEntity을 반환타입으로 사용한 이유는 상태코드를 같이 응답의 결과로 주기 위함
		
		log.info("ReplyVO: " + vo);						// 요청 데이터가 잘 도착했나 출력해봄
		
		int insertCount = service.register(vo);
		
		log.info("Reply INSERT COUNT: " + insertCount); // 처리결과 로그출력
		
		return insertCount == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)		// ajax에서 success : function(result, status, xhr){} 부분의 result쪽의로  "success", status쪽으로 200 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@GetMapping(value="/pages/{bno}/{page}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
//	public ResponseEntity<List<ReplyVO>> getList(
//											@PathVariable("page") int page, 
//											@PathVariable("bno") Long bno){ // @PathVariable 는 url에 사용된 값을 변수로 가져오기 위해 사용함
//		log.info("getList.............");
//		Criteria cri = new Criteria(page, 10);
//		log.info(cri);
//		
//		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
//	}
	
	@GetMapping(value="/pages/{bno}/{page}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page){
		
		Criteria cri = new Criteria(page, 10);
		
		log.info("get Reply List bno: " + bno);
		log.info("cri:" + cri);
		
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	@GetMapping(value="/{rno}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		
		log.info("get: " + rno);
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{rno}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove: " + rno);
		return service.remove(rno) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH}, value="/{rno}", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
										@RequestBody ReplyVO vo, 
										@PathVariable("rno") Long rno){
		
		vo.setRno(rno);
		log.info("rno: " + rno);
		log.info("modify: " + vo);
		
		return service.modify(vo) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
