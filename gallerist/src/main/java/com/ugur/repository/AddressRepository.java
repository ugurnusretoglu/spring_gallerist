package com.ugur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ugur.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	@Query(nativeQuery = true, value = "select count(*) > 0 from gallerist.customer where address_id=:id")
	public boolean existsCustomerByAddressId(Long id);
	
	@Query(nativeQuery = true, value = "select count(*) > 0 from gallerist.gallerist where address_id=:id")
	public boolean existsGalleristByAddressId(Long id);
	
}