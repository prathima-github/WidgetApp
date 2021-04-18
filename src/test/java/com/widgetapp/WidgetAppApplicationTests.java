package com.widgetapp;


import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.widgetapp.model.Widget;
import com.widgetapp.repository.WidgetRepository;
import com.widgetapp.service.WidgetService;

import amazo.Version;

@SpringBootTest

class WidgetAppApplicationTests {

	@Autowired
	private WidgetService service;

	@MockBean
	private WidgetRepository repo;

	@Test
	void contextLoads() {
	}


	
	@DisplayName("Test findById success")
	void testFindById() {
		Widget widget = new Widget(11L, "Widget Name", "This is my first widget", 1);
		doReturn(Optional.of(widget)).when(repo).findById(11L);
		Optional<Widget> test = service.findById(11L);
		Assertions.assertTrue(test.isPresent(), "Widget is not present");
		Assertions.assertSame(test.get(), widget, "Widget is not same");

	}
	
	@Test(expected = IllegalArgumentException.class)
    public void exampleTest1() {
        Widget version = new Widget(null, null, null, 0);
        rule.expect(IllegalArgumentException.class);
        rule.expectMessage("'version' must not be null!");
        // ...
    }
	
	
	void testFindById_NotFound() {
		Widget widget = new Widget(11L, "Widget Name", "This is my first widget", 1);
		doReturn(Optional.empty()).when(repo).findById(11L);
		Optional<Widget> test = service.findById(11L);
		assertFalse(test.isPresent(),"Id should not be present");
		
		
		
	}
	
	
	
	
	@DisplayName("Test findAll success")
	void testFindAll() {
		Widget widget1 = new Widget(12L, "Widget Name", "This is my first widget", 1);
		Widget widget2 = new Widget(13L, "Widget Name", "This is my first widget", 1);
		Widget widget3 =  new Widget(14L, "Widget Name", "This is my first widget", 1);
		List<Widget> widgets =new ArrayList<>();
		widgets.add(widget1);
		widgets.add(widget2);
		widgets.add(widget3);
				
		doReturn(widgets).when(repo).findAll();
		List<Widget> tests = service.findAll();
		Assertions.assertEquals(3,tests.size(),"findAll should return size 3");
		
	}
	
	void testSave() {
		Widget widget = new Widget(11L, "Widget name2", "this is my first widget",1);
		doReturn(widget).when(repo.save(any()));
		Widget test = service.save(widget);
		assertNotNull(test,"the saved widget is null");
		assertEquals(2,test.getVersion(),"Version is not updated");
				
	}
	
	
	
	
	
	

}
