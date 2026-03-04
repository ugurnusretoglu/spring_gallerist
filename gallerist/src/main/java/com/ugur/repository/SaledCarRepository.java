package com.ugur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ugur.entity.SaledCar;

@Repository
public interface SaledCarRepository  extends JpaRepository<SaledCar, Long>{

}
