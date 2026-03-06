package com.ugur.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugur.dto.CurrencyRatesResponse;
import com.ugur.dto.DtoAccount;
import com.ugur.dto.DtoAddress;
import com.ugur.dto.DtoCar;
import com.ugur.dto.DtoCustomer;
import com.ugur.dto.DtoGallerist;
import com.ugur.dto.DtoSaledCar;
import com.ugur.dto.DtoSaledCarIU;
import com.ugur.entity.Car;
import com.ugur.entity.Customer;
import com.ugur.entity.SaledCar;
import com.ugur.enums.CarStatusType;
import com.ugur.exception.BaseException;
import com.ugur.exception.ErrorMessage;
import com.ugur.exception.MessageType;
import com.ugur.repository.CarRepository;
import com.ugur.repository.CustomerRepository;
import com.ugur.repository.GalleristRepository;
import com.ugur.repository.SaledCarRepository;
import com.ugur.service.ICurrencyRatesService;
import com.ugur.service.ISaledCarService;
import com.ugur.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {
	
	@Autowired
	private SaledCarRepository saledCarRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd=new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
		
		return customerUSDAmount;
	}
	
	public BigDecimal remaningCustomerAmount(Customer customer, Car car) {
		BigDecimal customerUSDAmount =convertCustomerAmountToUSD(customer);
		BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd=new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		
		return remaningCustomerUSDAmount.multiply(usd);
	}
	
	
	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	}
	
	public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if(optCustomer.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		}
		
		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		}
		
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
		
		if(customerUSDAmount.compareTo(optCar.get().getPrice())==0 || 
				customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
			return true;
		}
		return false;
	}
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar=new SaledCar();
		saledCar.setCreateTime(new Date());
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		
		return saledCar;
	}

	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		if(!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		}
		if(!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		}
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
		
		Car car=savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer=savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
		customerRepository.save(customer);
		
		return toDTO(savedSaledCar);
	}
	
	public DtoSaledCar toDTO(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar=new DtoSaledCar();
		DtoCustomer dtoCustomer=new DtoCustomer();
		DtoGallerist dtoGallerist=new DtoGallerist();
		DtoCar dtoCar=new DtoCar();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		dtoSaledCar.setCar(dtoCar);
		
		return dtoSaledCar;
		
	}
	
	public SaledCar getSaledCarById(Long id) {
		Optional<SaledCar> optSaledCar = saledCarRepository.findById(id);
		if(optSaledCar.isPresent()) {
			return optSaledCar.get();
		}
		else {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, optSaledCar.get().getId().toString()));
		}
	}
	
	@Override
	public void deleteSaledCar(Long id) {
		SaledCar dbSaledCar=getSaledCarById(id);
		if(dbSaledCar!=null) {
			saledCarRepository.delete(dbSaledCar);
		}
		else {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}
	}

	@Override
	public List<DtoSaledCar> getAllSaledCars() {
		List<DtoSaledCar> dtoList=new ArrayList<>();
		List<SaledCar> saledCarList = saledCarRepository.findAll();
		
		for (SaledCar saledCar : saledCarList) {
			DtoSaledCar dto=new DtoSaledCar();
			DtoCustomer dtoCustomer=new DtoCustomer();
			DtoCar dtoCar=new DtoCar();
			DtoGallerist dtoGallerist=new DtoGallerist();
			DtoAddress dtoAddress=new DtoAddress();
			DtoAddress dtoAddress2=new DtoAddress();
			DtoAccount dtoAccount=new DtoAccount();
			
			BeanUtils.copyProperties(saledCar, dto);
			BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
			BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
			BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
			BeanUtils.copyProperties(saledCar.getCustomer().getAddress(), dtoAddress);
			BeanUtils.copyProperties(saledCar.getCustomer().getAccount(), dtoAccount);
			BeanUtils.copyProperties(saledCar.getGallerist().getAddress(), dtoAddress2);
			
			dtoCustomer.setAddress(dtoAddress);
			dtoCustomer.setAccount(dtoAccount);
			dtoGallerist.setAddres(dtoAddress2);
			dto.setCustomer(dtoCustomer);
			dto.setCar(dtoCar);
			dto.setGallerist(dtoGallerist);
			
			dtoList.add(dto);
		}
		return dtoList;
	}
}