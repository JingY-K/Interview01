package com.work2022.interview.coindesk.model;

import java.util.Map;

import lombok.Data;

@Data
public class Coindesk {
	
	private CoindeskTime time;
	
	private String disclaimer;
	
	private String chartName;
	
	private Map<String, CoindeskBPI> bpi;

}
