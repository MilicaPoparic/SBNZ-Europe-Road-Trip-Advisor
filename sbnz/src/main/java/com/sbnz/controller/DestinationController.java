package com.sbnz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sbnz.dto.DestinationDTO;
import com.sbnz.dto.SearchDTO;
import com.sbnz.model.Destination;
import com.sbnz.service.DestinationService;

@RestController
@RequestMapping(value = "/api/destination", produces = MediaType.APPLICATION_JSON_VALUE)
public class DestinationController {

	@Autowired
	private DestinationService destinationService;

	public DestinationController() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DestinationDTO>> getAllDestinations() {
		List<Destination> destinations = destinationService.findAll();
		return new ResponseEntity<>(toDestinationDTOList(destinations), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DestinationDTO> getDestination(@PathVariable Long id) {
		Destination destination = destinationService.findOne(id);
		if (destination == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(destination.toDTO(), HttpStatus.OK);
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ResponseEntity<Page<DestinationDTO>> loadDestinationPage(Pageable pageable) {
//		Page<Destination> Destinations = DestinationService.findAll(pageable);
//		if (Destinations == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		Page<DestinationDTO> DestinationDTO = toDestinationDTOPage(Destinations);
//		return new ResponseEntity<>(DestinationDTO, HttpStatus.OK);
//	}

	@RequestMapping(method = RequestMethod.POST)
	//@PreAuthorize("hasRole('REGISTERED_USER')")
	public ResponseEntity<DestinationDTO> createDestination(@RequestBody DestinationDTO destinationDTO) {
		System.out.println("gde si");
		Destination destination;
//		if (!this.validateDestinationDTO(DestinationDTO)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		try {
			destination = destinationDTO.toEntity();
			destination = destinationService.create(destination);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(destination.toDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('REGISTERED_USER')")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<DestinationDTO> updateDestination(@RequestBody DestinationDTO destinationDTO,
			@PathVariable Long id) {
		Destination destination;
//		if (!this.validateDestinationDTO(DestinationDTO))
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			destination = destinationService.update(destinationDTO.toEntity(), id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(destination.toDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	//@PreAuthorize("hasRole('REGISTERED_USER')")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<String> deleteDestination(@PathVariable Long id) {
		try {
			destinationService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	private List<DestinationDTO> toDestinationDTOList(List<Destination> destinations) {
		List<DestinationDTO> destinationDTOS = new ArrayList<>();
		for (Destination Destination : destinations) {
			destinationDTOS.add(Destination.toDTO());
		}
		return destinationDTOS;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/filterByUserProfile")
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public ResponseEntity<List<DestinationDTO>> filterDestinationsByUserProfile() {
		List<Destination> destinations = new ArrayList<Destination>();
		try {
			destinations = destinationService.filterByUserProfile();
			return new ResponseEntity<>(toDestinationDTOList(destinations), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/filterBySearchParams")
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public ResponseEntity<List<DestinationDTO>> filterDestinationsBySearch(@RequestBody SearchDTO searchDTO) {
		List<Destination> destinations = new ArrayList<Destination>();
		try {
			destinations = destinationService.filterBySearchParams(searchDTO);
			return new ResponseEntity<>(toDestinationDTOList(destinations), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}

//	private Page<DestinationDTO> toDestinationDTOPage(Page<Destination> rates) {
//		Page<DestinationDTO> dtoPage = rates.map(new Function<Destination, DestinationDTO>() {
//			@Override
//			public DestinationDTO apply(Destination entity) {
//				DestinationDTO dto = entity.toDTO();
//				return dto;
//			}
//		});
//		return dtoPage;
//	}

//	private boolean validateDestinationDTO(DestinationDTO DestinationDTO) {
//		if (DestinationDTO.getUserId() == null)
//			return false;
//		if (DestinationDTO.getCulturalOfferId() == null)
//			return false;
//		if (DestinationDTO.getDate() == null)
//			return false;
//		if (DestinationDTO.getText() == null)
//			return false;
//
//		return true;
//	}
}
