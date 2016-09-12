package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.entity.Name;
import ua.service.NameService;
import ua.service.implementation.validator.NameValidator;

@Controller
public class NameController {
	@Autowired
	private NameService nameService;

	@InitBinder("name")
		protected void InitBinder(WebDataBinder binder) {
		binder.setValidator(new NameValidator(nameService));
	}

	@ModelAttribute("name")
	public Name getName() {
		return new Name();
	}

	@RequestMapping("/admin/name")
	public String showName(Model model,
			@PageableDefault(size = 5, sort = "names") Pageable pageble) {
		model.addAttribute("names", nameService.findAllPageble(pageble));
		return "adminName";
	}

	@RequestMapping(value = "/admin/name", method = RequestMethod.POST)
	public String saveName(@ModelAttribute("name") @Valid Name name,
			BindingResult br, Model model,
			@PageableDefault(size = 5) Pageable pageable) {
		if (br.hasErrors()) {
			model.addAttribute("names", nameService.findAllPageble(pageable));
			return "adminName";
		}
		nameService.save(name);
		return "redirect:/admin/name";
	}

	@RequestMapping("/admin/name/delete/{id}")
	public String deleteName(@PathVariable int id) {
		nameService.deleteById(id);
		return "redirect:/admin/name";
	}

	@RequestMapping("/admin/name/update/{id}")
	public String updateName(@PathVariable int id, Model model,
			@PageableDefault(size = 5) Pageable pageable) {
		model.addAttribute("name", nameService.findById(id));
		model.addAttribute("names", nameService.findAllPageble(pageable));
		return "adminName";
	}
}
