package com.work2022.interview.coindesk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.work2022.interview.coindesk.model.NewAPIInfo;


public interface NewAPIInfoRepository extends Repository<NewAPIInfo, Long> {

	@Query(value = "SELECT b.id, b.code, c.name, b.rate, null updated FROM CoindeskBPI b left join Currency c on b.code = c.code WHERE b.json_id = (select max(id) from Coindesk_Json)", nativeQuery = true)
	List<NewAPIInfo> getNewAPI();
	
}
