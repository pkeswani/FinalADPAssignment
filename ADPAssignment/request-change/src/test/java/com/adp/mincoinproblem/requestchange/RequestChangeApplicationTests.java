package com.adp.mincoinproblem.requestchange;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.adp.mincoinproblem.requestchange.service.RequestChangeService;

@SpringBootTest
class RequestChangeApplicationTests {

	//@Autowired
	RequestChangeService rcs = new RequestChangeService();
	//@Test
	void contextLoads() {
	}
	
	@Test
	void testChange()
	{
		try {
			rcs.getMinCoins(20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
