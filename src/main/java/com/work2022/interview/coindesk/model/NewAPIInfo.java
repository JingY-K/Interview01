package com.work2022.interview.coindesk.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class NewAPIInfo {

	@Id
	private Long id;

	private String updated;

	private String code;

	private String name;

	private String rate;

}
