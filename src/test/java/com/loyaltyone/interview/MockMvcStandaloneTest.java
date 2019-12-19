package com.loyaltyone.interview;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyaltyone.controller.PostController;
import com.loyaltyone.entity.Submissions;
import com.loyaltyone.service.SubmissionsService;

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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


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
		public void getSubmissionTestWhenNameExists() throws Exception 
		{
			given(submissionsService.findSubmissionsByName("tua"))
            .willReturn(List.of(new Submissions(32,"hello", "tua", "res")));

    // when
    MockHttpServletResponse response = mvc.perform(
            get("/getsubmission/tua")
                    .accept(MediaType.APPLICATION_JSON))
            .andReturn().getResponse();

    // then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(
    		submissionssuperheroList.write(List.of(new Submissions(32,"hello", "tua", "res"))).getJson());
		}
		
	/*
	 * @Test public void getSubmissionTestWhenNameNotExists() throws Exception {
	 * List<Submissions> l = new ArrayList<Submissions>(); // given
	 * given(submissionsDao.findSubmissionsByName("xyz")) .willReturn(l);
	 * 
	 * // when MockHttpServletResponse response = mvc.perform(
	 * get("/superheroes/xyz") .accept(MediaType.APPLICATION_JSON))
	 * .andReturn().getResponse();
	 * 
	 * // then assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	 * assertThat(response.getContentAsString()).isEqualTo("null"); }
	 */

	    @Test
	    public void postSubmissionTest() throws Exception {
	        // when
	        MockHttpServletResponse response = mvc.perform(
	                post("/postsubmission/").contentType(MediaType.APPLICATION_JSON).content(
	                		submissionssuperhero.write(new Submissions("she is very dangerous", "bojjo", "toronto","12.3322","-30.444")).getJson()
	                )).andReturn().getResponse();

	        // then
	        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	    }
		
}
