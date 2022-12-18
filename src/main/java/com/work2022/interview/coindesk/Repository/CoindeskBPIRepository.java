package com.work2022.interview.coindesk.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work2022.interview.coindesk.model.CoindeskBPI;

public interface CoindeskBPIRepository extends JpaRepository<CoindeskBPI, Long> {

}
