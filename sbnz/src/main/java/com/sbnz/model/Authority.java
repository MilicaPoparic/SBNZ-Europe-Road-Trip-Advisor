package com.sbnz.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.sbnz.dto.AuthorityDTO;

@Entity
public class Authority implements GrantedAuthority{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String role;

	public Authority() {
		super();
	}

	public Authority(String role) {
		super();
		this.role = role;
	}
	
	public Authority(Long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	
	public AuthorityDTO toDTO() {
		return new AuthorityDTO(this.id, this.role);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return role;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority a = (Authority) o;
        if (a.getId() == null || id == null) {
            if(a.getRole().equals(getRole())){
                return true;
            }
            return false;
        }
        return Objects.equals(id, a.getId());
    }

}