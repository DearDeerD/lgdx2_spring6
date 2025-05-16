package com.lgdx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lgdx.entity.Board;
import com.lgdx.repository.BoardRepository;


@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository ;

	public List<Board> boardList(){ // 게시글 전체 가져오기
		return repository.findAll() ; 
	}

	public Board boardContents(Long idx) {//상세보기
		Optional<Board> vo = repository.findById(idx);
		return vo.get();
	}

	public void boardInsert(Board vo) {//글쓰기
		repository.save(vo);
	}

	public void boardDelete(Long idx) {//삭제기능
		repository.deleteById(idx);	
	}
	
	public void boardUpdate(Board vo) {//수정
		repository.save(vo);		
	}

	public void boardCount(Long idx) {//조회수	// 없는 기능 만들어 넣기
		repository.count(idx);
	}
	
}
