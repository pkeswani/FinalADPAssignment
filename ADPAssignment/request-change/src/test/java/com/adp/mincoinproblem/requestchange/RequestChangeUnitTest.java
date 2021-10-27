package com.adp.mincoinproblem.requestchange;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.adp.mincoinproblem.requestchange.service.RequestChangeService;
import com.adp.mincoinproblem.requestchange.web.RequestChangeController;

@RunWith(SpringRunner.class)
@WebMvcTest(RequestChangeController.class)
public class RequestChangeUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	RequestChangeService rcs;
	
	@Test
	public void requestChangeWithMinCoins() throws Exception{
		
		mockMvc.perform(get("/reqChange/coins/"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			   .andExpect(content().json("[]"));
		
		verify(rcs,times(1)).getMinCoins(100);
	}
	
	
}
