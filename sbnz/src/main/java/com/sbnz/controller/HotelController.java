package com.sbnz.controller;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.dto.HotelDTO;
import com.sbnz.model.Hotel;
import com.sbnz.model.RegisteredUser;
import com.sbnz.service.HotelService;
import com.sbnz.service.RegisteredUserService;

@RestController
@RequestMapping(value = "/api/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {

	@Autowired
	private HotelService hotelService;

	public HotelController() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<HotelDTO>> getAllHotels() {
		List<Hotel> hotels = hotelService.findAll();
		return new ResponseEntity<>(toHotelDTOList(hotels), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<HotelDTO> getHotel(@PathVariable Long id) {
		Hotel hotel = hotelService.findOne(id);
		if (hotel == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(hotel.toDTO(), HttpStatus.OK);
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ResponseEntity<Page<HotelDTO>> loadHotelPage(Pageable pageable) {
//		Page<Hotel> Hotels = HotelService.findAll(pageable);
//		if (Hotels == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		Page<HotelDTO> HotelDTO = toHotelDTOPage(Hotels);
//		return new ResponseEntity<>(HotelDTO, HttpStatus.OK);
//	}

	@RequestMapping(method = RequestMethod.POST)
	////@PreAuthorize("hasRole('REGISTERED_USER')")
	public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
		Hotel hotel;
//		if (!this.validateHotelDTO(hotelDTO)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		try {
			hotel = hotelDTO.toEntity();
			hotel = hotelService.create(hotel);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(hotel.toDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//	////@PreAuthorize("hasRole('REGISTERED_USER')")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<HotelDTO> updateHotel(@RequestBody HotelDTO hotelDTO,
			@PathVariable Long id) {
		Hotel hotel;
//		if (!this.validateHotelDTO(HotelDTO))
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			hotel = hotelService.update(hotelDTO.toEntity(), id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(hotel.toDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	////@PreAuthorize("hasRole('REGISTERED_USER')")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
		try {
			hotelService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	private List<HotelDTO> toHotelDTOList(List<Hotel> hotels) {
		List<HotelDTO> hotelDTOS = new ArrayList<>();
		for (Hotel hotel : hotels) {
			hotelDTOS.add(hotel.toDTO());
		}
		return hotelDTOS;
	}

//	private Page<HotelDTO> toHotelDTOPage(Page<Hotel> rates) {
//		Page<HotelDTO> dtoPage = rates.map(new Function<Hotel, HotelDTO>() {
//			@Override
//			public HotelDTO apply(Hotel entity) {
//				HotelDTO dto = entity.toDTO();
//				return dto;
//			}
//		});
//		return dtoPage;
//	}

//	private boolean validateHotelDTO(HotelDTO HotelDTO) {
//		if (HotelDTO.getUserId() == null)
//			return false;
//		if (HotelDTO.getCulturalOfferId() == null)
//			return false;
//		if (HotelDTO.getDate() == null)
//			return false;
//		if (HotelDTO.getText() == null)
//			return false;
//
//		return true;
//	}
}
