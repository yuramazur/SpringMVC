package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.service.NameService;

@Controller
public class NameController {
	@Autowired
	private NameService nameService;

	@RequestMapping("/admin/name")
	public String showName(Model model) {
		model.addAttribute("names", nameService.findAll());
		return "adminName";
	}
//	@RequestMapping("/admin/name")
//	public String showName(Model model,@RequestParam String namesSearch) {
//		if(namesSearch == null){
//		model.addAttribute("names", nameService.findAll());
//		}else{
//			model.addAttribute("names", nameService.findAllByCoincidence(namesSearch));
//		}
//		return "adminName";
//	}
	
	@RequestMapping(value = "/admin/name", method = RequestMethod.POST)
	public String saveName(@RequestParam String names){
		nameService.save(names);		
		return "redirect:/admin/name";
	}
	
	@RequestMapping(value = "/admin/name/search",method = RequestMethod.POST)
	public String searchName(Model model, @RequestParam String names) {
		model.addAttribute("names", nameService.findAllByCoincidence(names));
		model.addAttribute("size", nameService.findAllByCoincidence(names).size());
		return "adminNameSearch";
	}
	
	@RequestMapping("/admin/name/delete/{id}")
	public String deleteName(@PathVariable int id){
		nameService.deleteById(id);
		return "redirect:/admin/name";
	}
}
