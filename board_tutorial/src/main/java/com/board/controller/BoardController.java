package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
	// 게시물 목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void getList(Model model) throws Exception {
		List<BoardVO> list=null;
		list=service.list();
		
		model.addAttribute("list",list);
	   
	}
	
	//게시물 작성
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public void getWrite() throws Exception{
		
	}
	
	//게시물 작성
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String postWrite(BoardVO vo) throws Exception{
		service.write(vo);
		return "redirect:/board/list";
	}
	
	//게시물 조회
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno,Model model) throws Exception{
		BoardVO vo=service.view(bno);
		model.addAttribute("view",vo);
	}
	
	//게시물 수정
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void getModift(@RequestParam("bno") int bno,Model model) throws Exception{
		BoardVO vo=service.view(bno);
		model.addAttribute("view",vo);
	}
	
	//게시물 수정
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String postModify(BoardVO vo) throws Exception{
		service.modify(vo);
		return "redirect:/board/view?bno="+vo.getBno();
	}
	
	//게시물 삭제
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String getDelete(@RequestParam("bno") int bno) throws Exception{
		service.delete(bno);
		return "redirect:/board/list";
	}
	
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {
		Page page=new Page();
		
		page.setNum(num);
		page.setCount(service.count());
		
		List<BoardVO> list=null;
		list=service.listPage(page.getDisplayPost(),page.getPostNum());
				
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		
		/*
		model.addAttribute("pageNum", page.getPageNum());

		model.addAttribute("startPageNum", page.getStartPageNum());
		model.addAttribute("endPageNum", page.getEndPageNum());
		 
		model.addAttribute("prev", page.getPrev());
		model.addAttribute("next", page.getNext());  
		*/
		
		model.addAttribute("select", num);
		/*
		int count=service.count();
		int postNum=10;
		int pageNum=(int)Math.ceil((double)count/postNum);
		int displayPost=(num-1)*postNum;
		int pageNum_cnt=10;
		int endPageNum=(int)(Math.ceil((double)num/(double)pageNum_cnt)*pageNum_cnt);
		int startPageNum=endPageNum-(pageNum_cnt-1);
		int endPageNum_tmp=(int)(Math.ceil((double)count/(double)pageNum_cnt));
		if(endPageNum>endPageNum_tmp){
			endPageNum=endPageNum_tmp;
		}
		
		boolean prev=startPageNum==1?false:true;
		boolean next=endPageNum*pageNum_cnt>=count?false:true;
		List list=null;
		list=service.listPage(displayPost,postNum);
		model.addAttribute("list",list);
		model.addAttribute("pageNum",pageNum);
		
		model.addAttribute("startPageNum",startPageNum);
		model.addAttribute("endPageNum",endPageNum);
		
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		
		model.addAttribute("select",num);
		*/
	}
	
	
	
}