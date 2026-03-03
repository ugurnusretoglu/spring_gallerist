package com.ugur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ugur.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
