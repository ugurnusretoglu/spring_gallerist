package com.ugur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ugur.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	@Query(nativeQuery = true, value = "select count(*) > 0 from gallerist.customer where account_id=:id")
	public boolean existsCustomerByAccountId(Long id);
	
}
