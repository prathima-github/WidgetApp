package com.widgetapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.widgetapp.model.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Long> {
	

}
