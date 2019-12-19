package com.loyaltyone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loyaltyone.ExceptionHandler.DefaultExceptionHandler;
import com.loyaltyone.ExceptionHandler.SubmissionsNotfoundException;
import com.loyaltyone.entity.Submissions;
import com.loyaltyone.service.SubmissionsService;
import com.loyaltyone.service.Temperature;

@RestController
public class PostController {
	Logger logger = LoggerFactory.getLogger(PostController.class);
	@Autowired SubmissionsService submissionDao;
	@Autowired Temperature temerature;
	/*
	 * Persist the name and submission
	 */
	@PostMapping(path="/postsubmission")	
	public Submissions postSubmission(@RequestBody Submissions submissions) {
	                  logger.info("User value passed is"+submissions.getName()+submissions.getPost());
	                  submissionDao.save(submissions);
	                               return submissions;} 
	/*
	 * Fetch all submissions by Name
	 */
	
	  @GetMapping(path="/getsubmission/{name}") 
	 public List<Submissions> getSubmission(@PathVariable String name) throws IOException, SubmissionsNotfoundException {
	 logger.info("Name"+name);
	         List<Submissions> listUser=  submissionDao.findSubmissionsByName(name);
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
	 
	 @PostMapping(path="/postsubmission/response")
	public Submissions postResponse (@RequestBody Submissions submissions ){
		 logger.info("id"+ submissions.getPostid());
		  Submissions submit =submissionDao.getOne(submissions.getPostid());
	                  submit.setResponse(submissions.getResponse());
	                  Submissions listSubmission= submissionDao.save(submit);
		    		  return listSubmission;
		  
	  }
	 
}
