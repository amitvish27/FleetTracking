package com.fleet.trucker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fleet.trucker.entity.Reading;
import com.fleet.trucker.exception.BadRequestException;
import com.fleet.trucker.exception.ResourceNotFoundException;
import com.fleet.trucker.repository.ReadingRepository;

@Service
public class ReadingServiceImpl implements ReadingService {

	@Autowired
	private ReadingRepository repository;

	@Autowired
	private AlertService alertService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Reading> findAll() {
		return (List<Reading>) repository.findAll();
	}

	@Override
	public Reading findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reading with id {" + id + " } not found"));
	}

	@Override
	public List<Reading> findAllByVin(String vin) {
		return (List<Reading>) repository.findAllByVin(vin);
	}

	@Override
	public Reading create(Reading read) {
		Optional<Reading> exists = repository.findById(read.getId());
		if (exists.isPresent()) {
			throw new BadRequestException("Reading with id {" + exists.get().getId() + "} already exists");
		} else {
			alertService.create(read); // Creates Alerts depending on the RulesEngine
			return repository.save(read);			
		}
	}

	@Override
	public Reading update(String id, Reading read) {
		Optional<Reading> exists = repository.findById(read.getId());
		if (!exists.isPresent()) {
			throw new ResourceNotFoundException("Reading with id {" + exists.get().getId() + "} does not exists");
		} else {
			return repository.save(read);
		}
	}

	@Override
	public void delete(String id) {
		Reading exists = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reading with id {" + id + "} does not exists"));
		repository.delete(exists);
	}
}
