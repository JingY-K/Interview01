package com.work2022.interview.coindesk.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CoindeskTime {
	
	public String updated;
	

//    @JsonFormat(pattern= DateTimeFormatter.ISO_DATE_TIME.toString())
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	public LocalDateTime updatedISO;
    
//    private String updatedISO;
	
    public String updateduk;

}
