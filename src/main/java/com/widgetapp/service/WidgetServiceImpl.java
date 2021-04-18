package com.widgetapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.widgetapp.model.Widget;
import com.widgetapp.repository.WidgetRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class WidgetServiceImpl implements WidgetService {
	
	private WidgetRepository repo;

	@Override
	public Optional<Widget> findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public List<Widget> findAll() {
		// TODO Auto-generated method stub
		return (List<Widget>)repo.findAll();
	}

	@Override
	public Widget save(Widget widget) {
		// TODO Auto-generated method stub
		widget.setVersion(widget.getVersion()+1);
		return repo.save(widget);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);

	}

}
