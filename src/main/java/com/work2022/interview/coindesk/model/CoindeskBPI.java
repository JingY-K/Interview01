package com.work2022.interview.coindesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CoindeskBPI")
public class CoindeskBPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "json_id")
    private CoindeskJson josn;
    
	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String symbol;
	
	@Column(nullable = false)
	private String rate;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private String rate_float;
	
}
