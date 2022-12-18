package com.work2022.interview.coindesk;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.work2022.interview.coindesk.model.Currency;
import com.work2022.interview.coindesk.service.CurrencyService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class CoindeskApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private CurrencyService currencyService;
    

	@Test
	public void testInsert() {
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

	    JSONObject request = new JSONObject();
		try {
			request = new JSONObject()
			        .put("name", "美金")
			        .put("code", "USD")
			        .put("createBy", "-1")
			        .put("lastUpdateBy", "-1");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	    RequestBuilder requestBuilder =
	            MockMvcRequestBuilders
	                    .post("/currency/update")
	                    .headers(httpHeaders)
	                    .content(request.toString());
	    try {
	    	String result = mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().string(containsString("OK")))
            .andReturn().getResponse().getContentAsString();
	    	
            System.out.println("testInsert result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback
	public void testQuery() {
		Currency currency = new Currency();
		currency.setCode("USD");
		currency.setName("美金");
		currency.setCreateBy("-1");
		currency.setLastUpdateBy("-1");
		
		currencyService.update(currency);
		
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
	    
	    RequestBuilder requestBuilder =
	            MockMvcRequestBuilders
	                    .get("/currency/getByCode?code=USD")
	                    .headers(httpHeaders);
	    try {
	    	String result = mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(jsonPath("$.name").value("美金"))
			.andExpect(jsonPath("$.code").value("USD"))
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
	    	
            System.out.println("testQuery result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback
	public void testUpdate() {
		Currency currency = new Currency();
		currency.setCode("USD");
		currency.setName("美金");
		currency.setCreateBy("-1");
		currency.setLastUpdateBy("-1");
		
		currencyService.update(currency);
		
		HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

	    JSONObject request = new JSONObject();
		try {
			request = new JSONObject()
			        .put("name", "美元");
		} catch (JSONException e) {
			e.printStackTrace();
		}

	    RequestBuilder requestBuilder =
	            MockMvcRequestBuilders
	                    .post("/currency/update")
	                    .headers(httpHeaders)
	                    .content(request.toString());
	    try {
	    	String result = mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().string(containsString("OK")))
            .andReturn().getResponse().getContentAsString();
	    	
            System.out.println("testUpdate result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	@Rollback
	public void testDelete() {
		Currency currency = new Currency();
		currency.setCode("USD");
		currency.setName("美金");
		currency.setCreateBy("-1");
		currency.setLastUpdateBy("-1");
		
		currencyService.update(currency);
		
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
	    
	    RequestBuilder requestBuilder =
	            MockMvcRequestBuilders
	                    .get("/currency/deleteByCode?code=USD")
	                    .headers(httpHeaders);
	    try {
	    	String result = mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().string(containsString("OK")))
            .andReturn().getResponse().getContentAsString();
	    	
            System.out.println("testDelete result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback
	public void testCallAPI() {
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
	    
	    RequestBuilder requestBuilder =
	            MockMvcRequestBuilders
	                    .get("/rawAPI")
	                    .headers(httpHeaders);
	    try {
	    	String result = mockMvc.perform(requestBuilder)
			.andDo(print())
//			.andExpect(jsonPath("$.name").value("美金"))
//			.andExpect(jsonPath("$.code").value("USD"))
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().string(containsString("bpi")))
            .andExpect((ResultMatcher) content().string(containsString("USD")))
            .andReturn().getResponse().getContentAsString();
	    	
            System.out.println("testCallAPI result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback
	public void testGetNewAPIInfo() {
		Currency currency = new Currency();
		currency.setCode("USD");
		currency.setName("美金");
		currency.setCreateBy("-1");
		currency.setLastUpdateBy("-1");
		
		currencyService.update(currency);
		
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
	    
	    RequestBuilder requestBuilder =
	            MockMvcRequestBuilders
	                    .get("/newAPIInfo")
	                    .headers(httpHeaders);
	    try {
	    	String result = mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(jsonPath("$.[0].code").value("USD"))
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
	    	
            System.out.println("testGetNewAPIInfo result: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
