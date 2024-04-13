package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Library;
import com.example.entity.Log;
import com.example.service.LibraryService;
import com.example.service.LogService;
import com.example.service.LoginUser;

@Controller
@RequestMapping("library")
public class LibraryController {

	private final LibraryService libraryService;
	private final LogService logService;

	@Autowired
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	@GetMapping
	public String index(Model model) {
		List<Library> libraries = this.libraryService.findAll();
		model.addAttribute("libraries", libraries);
		return "library/index";
	}
	
	@GetMapping("/borrow")
	public String borrowingForm(@RequestParam("id") Integer id, Model model) {
		Library library = libraryService.findById(id);
		model.addAttribute("library",library);
		return "library/borrowingForm";
	}
	
	@PostMapping("/borrow")
	@Autowired
	public String borrow(@RequestParam("id") Integer id, 
			@RequestParam("return_due_date") String returnDueDate,
			@AuthenticationPrincipal LoginUser loginUser) {
		Library library = libraryService.findById(id);
		
		library.setUserId(loginUser.getUserId());
		libraryService.updateLibrary(library);
		
		Log log = new Log();
		log.setLibraryId(id);
		log.setUser(loginUser.getUserId());
		log.setRentDate(LocalDateTime.now());
		LocalDateTime returnDueDateTime = LocalDateTime.parse(returnDueDate + "T00:00:00");
		log.setReturnDueDate(returnDueDateTime);
		log.setReturnDate(null);
		logService.save(log);
		return "redirect:/library";
	}
}