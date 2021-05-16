package com.sbnz.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "verification_token")
public class VerificationToken {
	private static final int expirationTime =24*60;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="token")
	private String token;
	
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	public VerificationToken() {
		super();
	}

	public VerificationToken(String token, User pacijent) {
		super();
		Calendar calendar = Calendar.getInstance();
		
		this.token = token;
		this.user = pacijent;
		this.createdDate = new Date(calendar.getTime().getTime());
		this.endDate = setEndDate(); 
	}

	private Date setEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Timestamp(calendar.getTime().getTime()));
		calendar.add(Calendar.MINUTE, expirationTime);
		return new Date(calendar.getTime().getTime());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public static int getExpirationtime() {
		return expirationTime;
	}

	
}
