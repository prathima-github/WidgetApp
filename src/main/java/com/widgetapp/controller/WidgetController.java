package com.widgetapp.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.widgetapp.model.Widget;
import com.widgetapp.service.WidgetService;

@RestController
public class WidgetController {
	@Autowired
	private WidgetService service;

	private static final Logger logger = LogManager.getLogger(WidgetController.class);
	@GetMapping("/widget/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		logger.info("coming to the service method for  - " + id);
		System.out.println("coming to findbyid");
		return service.findById(id).map(x -> {
			try {
				System.out.println("coming to map too");
				return ResponseEntity.ok().eTag(Integer.toString(x.getVersion()))
						.location(new URI("/widget/" + x.getId())).body(x);

			} catch (URISyntaxException e) {
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}).orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping(path="/widget", produces= {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public List<Widget> findAll() {
		
		logger.info("coming to the service method for all " );
		return service.findAll();
		//return "hello";
	}
	
	@PostMapping(value="/widget", consumes="application/json", produces="application/json")
    public ResponseEntity<Widget> createWidget(@RequestBody Widget widget) {
		System.out.println("coming to post request - ");
		System.out.println("Received widget: name: " + widget.getName() + ", description: " + widget.getDescription());
        logger.info("Received widget: name: " + widget.getName() + ", description: " + widget.getDescription());
        Widget newWidget = service.save(widget);

        try {
            return ResponseEntity
                    .created(new URI("/rest/widget/" + newWidget.getId()))
                    .eTag(Integer.toString(newWidget.getVersion()))
                    .body(newWidget);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		//return ResponseEntity.ok().build();
    }

}
