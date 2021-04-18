package com.widgetapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@ToString
@Setter
@Getter
@NoArgsConstructor
public class Widget implements Serializable {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;
	private String name;
	private String description;
	private int version;
	

}
