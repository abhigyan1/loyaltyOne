package com.loyaltyone.interview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyaltyone.controller.PostController;
import com.loyaltyone.entity.Submissions;
import com.loyaltyone.service.SubmissionsService;


@RunWith(MockitoJUnitRunner.class)
public class MockMvcStandaloneTest {
	 private MockMvc mvc;
	 @Mock
		private SubmissionsService submissionsService;
		@InjectMocks
		private PostController postController;
		private JacksonTester<Submissions> submissionssuperhero;
		private JacksonTester<List<Submissions>> submissionssuperheroList;
		@Before
		public void setup() {
			JacksonTester.initFields(this, new ObjectMapper());
			mvc = MockMvcBuilders.standaloneSetup(postController)
					.build();
		}
		@Test
		public void getSubmissionTestWhenNameExistsTests() throws Exception 
		{
			given(submissionsService.findSubmissionsByName("tua"))
            .willReturn(List.of(new Submissions(32,"hello", "tua", "res")));

    // when
    MockHttpServletResponse response = mvc.perform(
            get("/api/vi/getsubmission/tua")
                    .accept(MediaType.APPLICATION_JSON))
            .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(
    		submissionssuperheroList.write(List.of(new Submissions(32,"hello", "tua", "res"))).getJson());
		}
	

	
	  @Test public void postSubmissionTest() throws Exception { // when
	  MockHttpServletResponse response = mvc.perform(
	                     post("/api/vi/postsubmission/")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(submissionssuperhero
	                        .write(new Submissions("Hello this is my comment", "abbie",
	                                   "toronto","12.3322","-30.444")).getJson()))
			                .andReturn()
			                .getResponse();
	  // then
	  assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value()); }
	 
	  @Test
	  public void postResponseTests() throws Exception {
		  long postid=102;
		  given(submissionsService.getOne(postid))
          .willReturn(new Submissions(102,"respond2"));
      MockHttpServletResponse response = mvc.perform(
              post("/api/vi/postsubmission/response")
              .contentType(MediaType.APPLICATION_JSON)
              .content(submissionssuperhero
              .write(new Submissions(102,"bello","ananya","toronto","43.653226","-79.38318429999998","respond2")).getJson()))
              .andReturn()
              .getResponse();
      // then
      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value()); }
	  

	  
	  
}
