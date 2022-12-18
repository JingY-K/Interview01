package com.work2022.interview.coindesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work2022.interview.coindesk.Repository.CurrencyRepository;
import com.work2022.interview.coindesk.model.Currency;

@Service
public class CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	public Currency getById(Long id) {
		Optional<Currency> optCurrent = currencyRepository.findById(id);
		return optCurrent.isPresent() ? optCurrent.get() : new Currency();

//		Currency currency = currencyRepository.getById(id);
//		return currency;
	}
	
	public Currency getByCode(String code) {
		Currency current = currencyRepository.getByCode(code);
		return current != null? current: new Currency();
	}

	public List<Currency> getAll() {
		return currencyRepository.findAll();
	}

	public String update(Currency currency) {
		String result = "OK";
		try {
			currency.setStatus(0);
			currencyRepository.save(currency);
		} catch (Exception e) {
			e.printStackTrace();
			result = "NG";
		}
		return result;
	}

	public String delete(Long id) {
		String result = "OK";
		try {
//			Optional<Currency> optCurrent = currencyRepository.findById(id);
//			
//			if (optCurrent.isPresent()) {
//				Currency currency = optCurrent.get();
//				currency.setStatus(1);
//				
//				currencyRepository.save(currency);
//			}
			
			currencyRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result = "NG";
		}
		return result;
	}
	
	public String deleteByCode(String code) {
		String result = "OK";
		try {
			currencyRepository.deleteByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
			result = "NG";
		}
		return result;
	}

}
