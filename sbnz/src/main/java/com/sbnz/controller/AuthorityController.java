package com.sbnz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.dto.AuthorityDTO;
import com.sbnz.model.Authority;
import com.sbnz.service.AuthorityService;


@RestController
@RequestMapping(value = "/api/authority", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorityController {
	
	@Autowired
	private AuthorityService authorityService;	
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<AuthorityDTO>> getAllAuthorities() {
		List<Authority> authories = authorityService.findAll();

		return new ResponseEntity<>(toAuthorityDTOList(authories), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthorityDTO> getById(@PathVariable Long id){
		Authority authority = authorityService.findOne(id);
		if(authority == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(authority.toDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AuthorityDTO> createAuthority(@RequestBody AuthorityDTO authorityDTO){
		Authority authority;
		if(!this.validateAuthorityDTO(authorityDTO))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			authority = authorityService.create(authorityDTO.toEntity());
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(authority.toDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AuthorityDTO> updateAuthority(@RequestBody AuthorityDTO authorityDTO,  @PathVariable Long id){
		Authority authority;
		if(!this.validateAuthorityDTO(authorityDTO))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			authority = authorityService.update(authorityDTO.toEntity(), id);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(authority.toDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteAuthority(@PathVariable Long id){
		try {
			authorityService.delete(id);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	private boolean validateAuthorityDTO(AuthorityDTO authorityDTO) {
		if(authorityDTO.getRole() == null)
			return false;
		return true;
	}
	private List<AuthorityDTO> toAuthorityDTOList(List<Authority> authorities){
        List<AuthorityDTO> authoritiesDTO = new ArrayList<>();
        for (Authority authority: authorities) {
        	authoritiesDTO.add(authority.toDTO());
        }
        return authoritiesDTO;
    }

}
