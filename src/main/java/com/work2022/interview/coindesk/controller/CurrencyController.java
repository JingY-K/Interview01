package com.work2022.interview.coindesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work2022.interview.coindesk.model.Currency;
import com.work2022.interview.coindesk.service.CurrencyService;

@RestController
@RequestMapping("currency")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;
	
	@GetMapping("get")
	public Currency getById(Long id) {
		Currency currency = currencyService.getById(id);
		return currency;
	}
	
	@GetMapping("getByCode")
	public Currency getByCode(String code) {
		Currency currency = currencyService.getByCode(code);
		return currency;
	}
	
	@GetMapping("all")
	public List<Currency> getAll() {
		List<Currency> currencyList = currencyService.getAll();
		return currencyList;
	}

	@PostMapping("update")
	public String update(@RequestBody Currency currency) {
		return currencyService.update(currency);
	}
	
	@GetMapping("delete")
	public String deleteById(Long id) {
		return currencyService.delete(id);
	}
	
	@GetMapping("deleteByCode")
	public String deleteByCode(String code) {
		return currencyService.deleteByCode(code);
	}
}
