package com.widgetapp;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.widgetapp.model.Widget;
import com.widgetapp.service.WidgetService;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
@MockBean
	private WidgetService service;
	
	
	@Autowired
	private MockMvc mockmvc;

	
	@Test
	
	void testGet() {
		Widget w1 = new Widget(1L,"widget2","hello this is my second widget",2);
		Widget w2 = new Widget(2L,"widget2","hello this is my second widget",2);
		List<Widget> list = new ArrayList<>();
		list.add(w1);
		list.add(w2);
		doReturn(list).when(service.findAll());
		
		try {
			mockmvc.perform(get("/widget")).
			andExpect(status().isOk()).
			andExpect(content().contentType(MediaType.APPLICATION_JSON)).
			andExpect(header().string(HttpHeaders.LOCATION,"/widget")).
			andExpect(jsonPath("$[0].id", is(1)));
			
			System.out.println("test success");
			
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
