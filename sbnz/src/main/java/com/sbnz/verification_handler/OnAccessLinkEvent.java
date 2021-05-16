package com.sbnz.verification_handler;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;
import com.sbnz.model.User;

public class OnAccessLinkEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private String path;
	private Locale locale;
	private User user;

	public OnAccessLinkEvent(User user, Locale locale, String path) {
		super(user);
		this.user = user;
		this.locale = locale;
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
