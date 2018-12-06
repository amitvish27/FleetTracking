package com.fleet.trucker.service;

import java.util.ArrayList;
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
	@Transactional(readOnly = true)
	public Reading findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reading with id {" + id + " } not found"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reading> findAllByVin(String vin) {
		return (List<Reading>) repository.findAllByVin(vin);
	}

	@Override
	@Transactional
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
	@Transactional
	public Reading update(String id, Reading read) {
		Optional<Reading> exists = repository.findById(read.getId());
		if (!exists.isPresent()) {
			throw new ResourceNotFoundException("Reading with id {" + exists.get().getId() + "} does not exists");
		} else {
			return repository.save(read);
		}
	}

	@Override
	@Transactional
	public void delete(String id) {
		Reading exists = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reading with id {" + id + "} does not exists"));
		repository.delete(exists);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String[]> findLastGeolocation(String vin) {
		final List<String[]> list = new ArrayList<>();
		if(findAllByVin(vin).isEmpty()){
			throw new ResourceNotFoundException("Readings for vin {" + vin + "} does not exists");
		} else {
			List<Reading> result = repository.findLastGeolocation(vin);
			result.iterator().forEachRemaining(r->{
				String[] array = new String[5];
				array[0] = r.getId();
				array[1] = r.getVin();
				array[2] = String.valueOf(r.getLatitude());
				array[3] = String.valueOf(r.getLongitude());
				array[4] = r.getTimestamp().toString();
				list.add(array);
			});
		}
		return list;
	}
}
