package com.loyaltyone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.loyaltyone.entity.Text;
import com.loyaltyone.service.TextService;



@RestController
public class TextController {
Logger logger = LoggerFactory.getLogger(TextController.class);

@Autowired TextService textService;

/*
 * Controller service that handles value passed to textBox by user
 */
@GetMapping(path="/gettext/{text}")	
public String returnText(@PathVariable String text) {
                            logger.info("Text value passed is"+text);
                          
                            Text textObject = new Text();
                            textObject.setTextfield(text);
                            textService.save(textObject);
                            return text;
                       } 

/*
 * Fallback method in case user does not pass any value to textbox
 */

  @GetMapping(path="/gettext") public String returnText() {
                  return "Please pass a value to text box";
                  }
                }
