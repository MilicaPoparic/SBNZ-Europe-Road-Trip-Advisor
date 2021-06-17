package com.sbnz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.Destination;
import com.sbnz.model.Hotel;
import com.sbnz.repository.DestinationRepository;
import com.sbnz.repository.HotelRepository;

@Service
public class HotelService implements ServiceInterface<Hotel> {

	@Autowired
	private HotelRepository HotelRepository;
	
	@Autowired
	private DestinationRepository destinationRepository;

	@Override
	public List<Hotel> findAll() {
		return HotelRepository.findByActive(true);
	}

	@Override
	public Hotel findOne(Long id) {
		return HotelRepository.findByIdAndActive(id, true).orElse(null);
	}

	@Override
	public Hotel create(Hotel entity) throws Exception {
		// make new Hotel instance
		Hotel c = new Hotel();
		c.setName(entity.getName());
		c.setStars(entity.getStars());
		c.setActive(true);
		c = HotelRepository.save(c);
		return c;
	}

	@Override
	public Hotel update(Hotel entity, Long id) throws Exception {
		Optional<Hotel> optHotel = HotelRepository.findById(id);
		if (!optHotel.isPresent()) {
			throw new Exception("Hotel with given id doesn't exist");
		}
		Hotel existingHotel = optHotel.orElse(null);

		existingHotel.setName(entity.getName());
		return HotelRepository.save(existingHotel);
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<Hotel> optHotel = HotelRepository.findById(id);
		if (!optHotel.isPresent()) {
			throw new Exception("Hotel with given id doesn't exist");
		}
		Hotel existingHotel = optHotel.orElse(null);
		existingHotel.setActive(false);
		HotelRepository.save(existingHotel);
	}

	public List<Hotel> findAllActive() {
		return HotelRepository.findByActive(true);
	}

	public Optional<Hotel> findHotelById(Long id) {
		return HotelRepository.findByIdAndActive(id, true);
	}

	public Hotel create(Hotel entity, Long destinationId) {
		Hotel c = new Hotel();
		c.setName(entity.getName());
		c.setStars(entity.getStars());
		c.setActive(true);
		c.setChildrenDiscount(entity.getChildrenDiscount());
		c = HotelRepository.save(c);
		
		Destination destination = destinationRepository.findById(destinationId).orElse(null);
		if (destination != null) {
			destination.getHotels().add(c);
			destinationRepository.save(destination);
		}
		return c;
	}

}
