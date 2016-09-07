package ua.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Name;
import ua.service.NameService;

public class NameEditor extends PropertyEditorSupport {

	private final NameService nameService;

	public NameEditor(NameService nameService) {
		this.nameService = nameService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Name name = nameService.findById(Integer.valueOf(text));
		setValue(name);
	}

}
