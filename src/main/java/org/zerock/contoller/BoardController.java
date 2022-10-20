package org.zerock.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	
	private BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) { // 컨트롤러 메소드가 void 타입이라면 호출하는 url과 같은 이름의 jsp 뷰가 반환됨. 즉 list.jsp가 반환됨(143p. 컨트롤러의 리턴타입 참고). view\board\list.jsp를 응답의 결과로 줌
//		log.info("list");
//		model.addAttribute("list", service.getList());
//	}

	@GetMapping("/list")
	public void list(Criteria cri, Model model) { // 컨트롤러 메소드가 void 타입이라면 호출하는 url과 같은 이름의 jsp 뷰가 반환됨. 즉 list.jsp가 반환됨(143p. 컨트롤러의 리턴타입 참고). view\board\list.jsp를 응답의 결과로 줌
		log.info("list");
		
		model.addAttribute("list", service.getList(cri));		// 해당 페이지번호에서 보여줄 레코드만 가져옴	
		
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));	// 어떤 페이지번호들 및 어떤 버튼을 보여줄지에 대한 정보가 담겨있는 객체를 화면에 넘겨줌 
		int total = service.getTotal(cri);
		log.info("total: " + total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));	// 어떤 페이지번호들 및 어떤 버튼을 보여줄지에 대한 정보가 담겨있는 객체를 화면에 넘겨줌		
	}
	
	@GetMapping("/register") // 게시물 입력폼
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register: " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list"; // 등록 작업이 끝난 후 다시 목록 화면으로 돌아가기 위함
	}
	
	@GetMapping({"/get", "/modify"})			// url이 get이면 get.jsp를 반환해주고, url이 modify이면 modify.jsp를 반환해 주는 것을 동시에 처리한 것이다.
	public void get(@RequestParam("bno") Long bno, 
					@ModelAttribute("cri") Criteria cri,		//모델어트리뷰트는 자동으로 지정한 이름으로 Model에 담아준다.(만약 이름을 지정해주지 않는다면 클래스명의 맨앞자리가 소문자인 이름으로 담아준다.)
					Model model) {
		log.info("/get or /modify");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, 
						@ModelAttribute("cri") Criteria cri,	//모델어트리뷰트는 자동으로 지정한 이름으로 Model에 담아준다.(만약 이름을 지정해주지 않는다면 클래스명의 맨앞자리가 소문자인 이름으로 담아준다.)
						RedirectAttributes rttr) {
		log.info("modify:" + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		//Criteria 를 파라미터로 추가하면서 추가해준 2줄의 코드
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list"; // 등록 작업이 끝난 후 다시 목록 화면으로 돌아가기 위함
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, 
						@ModelAttribute("cri") Criteria cri,
						RedirectAttributes rttr) {
		log.info("remove...." + bno);
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		//Criteria 를 파라미터로 추가하면서 추가해준 2줄의 코드
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}
	

}
