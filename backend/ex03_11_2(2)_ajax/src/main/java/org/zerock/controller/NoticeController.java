package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Notice;
import org.zerock.domain.NoticeDTO;
import org.zerock.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("")
	public String listNotices(Model model) {
		model.addAttribute("notices",noticeService.getAllNotices());
		return "notice/list";
	}
	
	@GetMapping("/{id}")
	public String viewNoitce(@PathVariable int id, Model model) {
		model.addAttribute("notice", noticeService.getNoticeById(id));
		return "notice/view";
	}
	
	@GetMapping("/new")
	public String newNoticeForm() {
		return "notice/form";
	}
	
	@PostMapping("")
	public String createNotice(@ModelAttribute Notice noitce) {
		noticeService.insertNotice(noitce);
		return "redirect:/notices";
	}
	
	@GetMapping("/{id}/edit")
	public String editNoticeForm(@PathVariable int id, Model model) {
		model.addAttribute("notice", noticeService.getNoticeById(id));
		return "notice/form";
	}
	
	@PostMapping("/{id}")
	public String updateNotice(@PathVariable int id, @ModelAttribute Notice notice) {
		notice.setId(id);
		noticeService.updateNotice(notice);
		return "redirect:/notices";
	}
	
	@DeleteMapping("/{id}")
	public String deleteNotice(@PathVariable int id) {
		noticeService.deleteNotice(id);
		return "redirect:/notices";
	}


}
