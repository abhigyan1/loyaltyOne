package com.loyaltyone.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
@Component

@Entity
public class Text extends Timestamp{
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Long id;	
private String textfield;

public String getTextfield() {
	return textfield;
}

public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public void setTextfield(String textfield) {
	this.textfield = textfield;
}
public Text(){};

/*public Text(String textfield, Long id, Date date){
	this.textfield=textfield;
	this.id=id;*/
	
			
}
