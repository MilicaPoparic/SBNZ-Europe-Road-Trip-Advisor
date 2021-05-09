package com.sbnz.controller;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.dto.UserDTO;
import com.sbnz.model.User;
import com.sbnz.service.UserService;


@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired 
	private UserService service;
	
	
	@RequestMapping(method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<User> users = service.findAll();
		return new ResponseEntity<>(toDTOUsersList(users), HttpStatus.OK);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	//@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> loadUsersPage(Pageable pageable) {
    	Page<User> users = service.findAll(pageable);
    	if(users == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	Page<UserDTO> usersDTO = toUsersDTOPage(users);
    	return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
		User u = service.findOne(id);
		if (u == null) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(u.toDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/currentUser", method=RequestMethod.GET)
	//@PreAuthorize("hasAnyRole('ADMIN', 'REGISTERED_USER')")
    public ResponseEntity<UserDTO> getCurrentUser(){
		 Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		 String username = ((User) currentUser.getPrincipal()).getEmail();
		 User user = service.findByEmail(username);
		 return new ResponseEntity<>(user.toDTO(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{email:.+}", method = RequestMethod.GET,produces=MediaType.ALL_VALUE, consumes=MediaType.ALL_VALUE)
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
		
		User u = service.findByEmail(email.toString());
		if (u == null) {
	
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(u.toDTO(), HttpStatus.OK);
	}

	public List<UserDTO> toDTOUsersList(List<User> users) {
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
        for (User u: users) {
        	listDTO.add(u.toDTO());
        }
        return listDTO;
	}
	
	private Page<UserDTO> toUsersDTOPage(Page<User> users) {
		Page<UserDTO> dtoPage = users.map(new Function<User, UserDTO>() {
		    @Override
		    public UserDTO apply(User entity) {
		    	UserDTO dto = entity.toDTO();
		        return dto;
		    }
		});
		return dtoPage;
	}

}
