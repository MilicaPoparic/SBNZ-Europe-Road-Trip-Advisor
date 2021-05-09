package com.sbnz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.Destination;
import com.sbnz.repository.DestinationRepository;

@Service
public class DestinationService implements ServiceInterface<Destination> {

	@Autowired
	private DestinationRepository DestinationRepository;

	@Override
	public List<Destination> findAll() {
		return DestinationRepository.findByActive(true);
	}

	@Override
	public Destination findOne(Long id) {
		return DestinationRepository.findByIdAndActive(id, true).orElse(null);
	}

	@Override
	public Destination create(Destination entity) throws Exception {
		// make new Destination instance
		Destination c = new Destination();
		c.setName(entity.getName());
		c.setLocationLat(entity.getLocationLat());
		c.setLocationLon(entity.getLocationLon());
		c.setLocalFood(entity.getLocalFood());
		c.setCategories(entity.getCategories());
		c.setHotels(entity.getHotels());
		c.setScore(entity.getScore());
		c.setTransportation(entity.getTransportation());
		c.setTrending(entity.getTrending());
		c.setActive(true);
		c = DestinationRepository.save(c);
		return c;
	}

	@Override
	public Destination update(Destination entity, Long id) throws Exception {
		Optional<Destination> optDestination = DestinationRepository.findById(id);
		if (!optDestination.isPresent()) {
			throw new Exception("Destination with given id doesn't exist");
		}
		Destination existingDestination = optDestination.orElse(null);

		existingDestination.setName(entity.getName());
		return DestinationRepository.save(existingDestination);
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<Destination> optDestination = DestinationRepository.findById(id);
		if (!optDestination.isPresent()) {
			throw new Exception("Destination with given id doesn't exist");
		}
		Destination existingDestination = optDestination.orElse(null);
		existingDestination.setActive(false);
		DestinationRepository.save(existingDestination);
	}

	public List<Destination> findAllActive() {
		return DestinationRepository.findByActive(true);
	}

	public Optional<Destination> findDestinationById(Long id) {
		return DestinationRepository.findByIdAndActive(id, true);
	}

}
