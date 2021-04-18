package com.widgetapp.service;

import java.util.List;
import java.util.Optional;

import com.widgetapp.model.Widget;

public interface WidgetService {
	Optional<Widget> findById(Long id);
	List<Widget> findAll();
	Widget save(Widget widget);
	void deleteById(Long id);
	
	

}
