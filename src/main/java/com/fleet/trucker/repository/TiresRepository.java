package com.fleet.trucker.repository;

import org.springframework.data.repository.CrudRepository;

import com.fleet.trucker.entity.Tires;

public interface TiresRepository extends CrudRepository<Tires, String> {

}
