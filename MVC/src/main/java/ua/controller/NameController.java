package ua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
import ua.form.filter.NameFilterForm;
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

	@ModelAttribute("filter")
	public NameFilterForm getFilter() {
		return new NameFilterForm();
	}

	@RequestMapping("/admin/name")
	public String showName(Model model,
			@PageableDefault(size = 5, sort = "names") Pageable pageable,
			@ModelAttribute("filter") NameFilterForm form) {
		model.addAttribute("page",
				nameService.findAllPagebleForm(pageable, form));
		return "adminName";
	}

	@RequestMapping(value = "/admin/name", method = RequestMethod.POST)
	public String saveName(@ModelAttribute("name") @Valid Name name,
			BindingResult br, Model model,
			@PageableDefault(size = 5) Pageable pageable,
			@ModelAttribute("filter") NameFilterForm form) {
		if (br.hasErrors()) {
			model.addAttribute("page",
					nameService.findAllPagebleForm(pageable, form));
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
			@PageableDefault(size = 5) Pageable pageable,
			@ModelAttribute("filter") NameFilterForm form) {
		model.addAttribute("name", nameService.findById(id));
		model.addAttribute("page",
				nameService.findAllPagebleForm(pageable, form));
		return "adminName";
	}
	@SuppressWarnings("unused")
	private String getParams(Pageable pageable, NameFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
	}
}
