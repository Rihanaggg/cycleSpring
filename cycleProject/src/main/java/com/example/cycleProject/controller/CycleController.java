package com.example.cycleProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.cycleProject.model.Cycle;
import com.example.cycleProject.service.AddNewCycle;



@Controller
public class CycleController {
	
	@Autowired
	public AddNewCycle service;
		
	 @GetMapping("/firstform")
	  public String firstForm(Model model) {
	    model.addAttribute("cycles", service.getAllCycles());

	    return "firstform";
	  }
	 
	 @GetMapping("/canBorrow/{id}")
	 public String canBorrow(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
	     Cycle cycle = service.borrow(id);
	     if (cycle != null) {
	         redirectAttributes.addFlashAttribute("message", "You can borrow this Cycle");
	     } else {
	         redirectAttributes.addFlashAttribute("message", "You cannot borrow this Cycle");
	     }
	     return "redirect:/firstform";
	 }

	 @GetMapping("/canReturn/{id}")
	 public String canReturn(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
	     Cycle cycle = service.Returncycle(id);
	     if (cycle != null) {
	         redirectAttributes.addFlashAttribute("msg", "Cycle Returned");
	     } else {
	         redirectAttributes.addFlashAttribute("msg", "You cannot Return Cycle");
	     }
	     return "redirect:/firstform";
	 }
	 
	 @GetMapping("/incrementCounter/{id}")
     public String incrementCounter(@PathVariable Long id, @RequestParam("incrementValue") int incrementValue) {
         Cycle cycle = service.getCycleById(id);
         if (cycle != null) {
             
             cycle.setCounter(cycle.getCounter() + incrementValue);            
             service.saveCycle(cycle);
         }
         return "redirect:/firstform";
     }
	 
	 @GetMapping("/stock")
     public String showStock(Model model) {
         List<Cycle> cycles = service.getAllCycles();
         int totalAvailableStock = cycles.stream().filter(Cycle::isAvailable).mapToInt(Cycle::getCounter).sum();
         model.addAttribute("totalAvailableStock", totalAvailableStock);
         return "stock";
     }
	 
}