package com.lgdx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgdx.entity.Board;
import com.lgdx.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/boardList.do")
	public String boardList(Model model) {
		List<Board> list = service.boardList(); // 모든 게시글 내용 반환
//		왜 array가 아닌 list를 받지?
//		 	부모타입으로 해줘야 확장성이 좋다.
		System.out.println(list.toString());
		model.addAttribute("list", list);
		return "boardList";
	}
	
//	상세보기
	@GetMapping("/boardContents.do")
	public String boardContents(@RequestParam("idx") Long idx, Model model) {
		
		service.boardCount(idx); // 조회수 올리기 기능
		
		Board vo = service.boardContents(idx);
		model.addAttribute("vo",vo);
		model.addAttribute("ln","\n");
		return "boardContents";
	}
	
//	글쓰기 기능
	@GetMapping("/boardInsert.do")
	public String boardInsert() {
		return "boardInsert";
	}
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) {
		service.boardInsert(vo);
		return "redirect:/boardList.do";
	}
	
//	삭제 기능
	@GetMapping("/boardDelete.do")
	public String boardDelete(@RequestParam("idx") Long idx) {
		service.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	
//	수정 기능
	@GetMapping("/boardUpdate.do")
	public String boardUpdate(@RequestParam("idx") Long idx, Model model) {
		Board vo = service.boardContents(idx);
		model.addAttribute("vo",vo);	//모델에 담아주기
		return "boardUpdate";
	}	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) {
		service.boardUpdate(vo);
		return "redirect:/boardList.do";
	}
	
	

}
