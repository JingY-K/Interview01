package com.work2022.interview.coindesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work2022.interview.coindesk.RestService.CoindeskRestService;
import com.work2022.interview.coindesk.model.NewAPIInfo;

@RestController
public class CoindeskController {

	@Autowired
	private CoindeskRestService coindeskRestService;

	@GetMapping("/rawAPI")
	public String getAPI() {
		return coindeskRestService.getAPI();
	}

	@GetMapping("/newAPIInfo")
	public List<NewAPIInfo> getNewAPIInfo() {
		return coindeskRestService.getNewAPIInfo();
	}
	
}
