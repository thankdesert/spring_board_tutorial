package com.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.ReplyVO;
import com.board.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	@Inject
	private ReplyService replyService;
	// λκΈ μμ±
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String posttWirte(ReplyVO vo) throws Exception {
		
		replyService.write(vo);
		
		return "redirect:/board/view?bno=" + vo.getBno();
	}
}
