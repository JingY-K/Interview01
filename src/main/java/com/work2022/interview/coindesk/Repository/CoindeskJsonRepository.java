package com.work2022.interview.coindesk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work2022.interview.coindesk.model.CoindeskJson;

public interface CoindeskJsonRepository  extends JpaRepository<CoindeskJson, Long> {

}
