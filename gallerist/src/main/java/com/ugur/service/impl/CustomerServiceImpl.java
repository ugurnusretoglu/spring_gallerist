package com.ugur.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugur.dto.DtoAccount;
import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoCustomer;
import com.ugur.dto.DtoCustomerIU;
import com.ugur.entity.Account;
import com.ugur.entity.Address;
import com.ugur.entity.Customer;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.repository.AccountRepository;
import com.ugur.repository.AddressRepository;
import com.ugur.repository.CustomerRepository;
import com.ugur.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
		}
		
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if(optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString())); 
		}
		
		Customer customer = new Customer();
		customer.setCreateTime(new Date());
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		
		customer.setAddress(optAddress.get());
		customer.setAccount(optAccount.get());
		
		return customer;
	}
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dtoCustomer=new DtoCustomer();
		DtoAddress dtoAddress=new DtoAddress();
		DtoAccount dtoAccount=new DtoAccount();
		
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
		
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		
		dtoCustomer.setAddress(dtoAddress);
		dtoCustomer.setAccount(dtoAccount);
		
		return dtoCustomer;
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customer=customerRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.RECORD_NOT_FOUND, id.toString())));
		
		customerRepository.delete(customer);
	}

	@Override
	public List<DtoCustomer> getAllCustomers() {
		List<DtoCustomer> dtoList=new ArrayList<>();
		List<Customer> customerList = customerRepository.findAll();
		
		for (Customer customer : customerList) {
			DtoAddress dtoAddress=new DtoAddress();
			DtoAccount dtoAccount=new DtoAccount();
			DtoCustomer dto=new DtoCustomer();
			BeanUtils.copyProperties(customer, dto);
			BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
			BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
			dto.setAddress(dtoAddress);
			dto.setAccount(dtoAccount);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dto=new DtoCustomer();
		DtoAccount dtoAccount=new DtoAccount();
		DtoAddress dtoAddress=new DtoAddress();
		Optional<Customer> optCustomer = customerRepository.findById(id);
		
		if(optCustomer.isPresent()) {
			Customer dbCustomer=optCustomer.get();
			
			dbCustomer.setFirstName(dtoCustomerIU.getFirstName());
			dbCustomer.setLastName(dtoCustomerIU.getLastName());
			dbCustomer.setTckn(dtoCustomerIU.getTckn());
			dbCustomer.setBirthOfDate(dtoCustomerIU.getBirthOfDate());
			
			Customer updatedCustomer=customerRepository.save(dbCustomer);
			
			BeanUtils.copyProperties(updatedCustomer, dto);
			BeanUtils.copyProperties(updatedCustomer.getAccount(), dtoAccount);
			BeanUtils.copyProperties(updatedCustomer.getAddress(), dtoAddress);
			dto.setAccount(dtoAccount);
			dto.setAddress(dtoAddress);
			
			return dto;
		}
		else {
			throw new BaseException(new ErrorMessage(MessageType.RECORD_NOT_FOUND, id.toString()));
		}
		
	}

}
