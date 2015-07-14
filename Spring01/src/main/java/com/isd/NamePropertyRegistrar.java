package com.isd;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

public class NamePropertyRegistrar implements PropertyEditorRegistrar {
	
	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		// TODO Auto-generated method stub
		registry.registerCustomEditor(Name.class, new NamePropertyEditor());
	}

}
