package com.loyaltyone.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loyaltyone.ExceptionHandler.SubmissionsNotfoundException;
import com.loyaltyone.entity.Submissions;
import com.loyaltyone.service.SubmissionsService;
import com.loyaltyone.service.Temperature;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/vi")
public class PostController {
	Logger logger = LoggerFactory.getLogger(PostController.class);
	@Autowired SubmissionsService submissionService;
	@Autowired Temperature temerature;
	/*
	 * Persist the name and submission
	 */
	@ApiOperation(value = "Post user info", response = Submissions.class)
	@PostMapping(path="/postsubmission")	
	public Submissions postSubmission(@RequestBody Submissions submissions) {
	                  logger.info("User value passed is"+submissions.getName()+submissions.getPost());
	                  submissionService.save(submissions);
	                               return submissions;} 
	/*
	 * Fetch all submissions by Name
	 */

	@ApiOperation(value = "get user info by passing name", response = List.class)
	@GetMapping(path="/getsubmission/{name}") 
	 public List<Submissions> getSubmission(@PathVariable String name) throws IOException, SubmissionsNotfoundException {
	 logger.info("Name"+name);
	         List<Submissions> listUser=  submissionService.findSubmissionsByName(name);
	                if(!(listUser.isEmpty())) {
	                     for(Submissions submission : listUser){
		                      logger.info("submission is here");
			                  if(submission.getCity()!=null) {
			                   logger.info("submission city is"+ submission.getCity());
				               String temperature =  temerature.getTemperatureFromApi(submission.getCity());
				               logger.info("temperature###"+temperature);
				               submission.setTemperature(temperature);
						          }
		                        }
			                  logger.info("listUser$$"+listUser);}
	                             else {throw new SubmissionsNotfoundException();}                             
	                                        return listUser;}
	  /*
	   * Update responses to the submission by postid
	   */
	@ApiOperation(value = "Submit response on post", response = Submissions.class)
	@PostMapping(path="/postsubmission/response")
	public Submissions postResponse (@RequestBody Submissions submissions ){
		  Submissions submit =submissionService.getOne(submissions.getPostid());
		              logger.info("postid"+submit.getPost());
	                  submit.setResponse(submissions.getResponse());
	                  Submissions Submission= submissionService.save(submit);
		    		  return Submission;
	  }
	}