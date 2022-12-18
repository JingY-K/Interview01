package com.work2022.interview.coindesk.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.work2022.interview.coindesk.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	@Query(value = "SELECT u FROM Currency u WHERE u.id = ?1 and u.status = 0", nativeQuery = false)
	Currency getById(Long id);
	

	@Query(value = "SELECT u FROM Currency u WHERE u.status = 0", nativeQuery = false)
	List<Currency> getAll();
	
	@Query(value = "SELECT u FROM Currency u WHERE u.code = ?1")
	Currency getByCode(String code);

	@Query(value = "DELETE Currency u WHERE u.code = ?1")
	void deleteByCode(String code);
	
}
