package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Date;

import com.soinsoftware.hotelero.persistence.bll.UserBll;
import com.soinsoftware.hotelero.persistence.entity.Company;
import com.soinsoftware.hotelero.persistence.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class UserController {

	private final UserBll bll;

	public UserController() throws IOException {
		super();
		bll = new UserBll();
	}

	public boolean isExistingUser(final long identification) {
		return (select(identification) != null);
	}

	public User select(final long identification) {
		return bll.select(identification);
	}

	public User selec(final String login, final char[] password) {
		final String strPassword = String.valueOf(password);
		return bll.select(login, strPassword);
	}

	public User save(final Company company, final long identification, final String name, final long phone,
			final String career) {
		User user = select(identification);
		final Date currentDate = new Date();
		if (user == null) {
			user = new User(company, identification, name, phone, career, currentDate, currentDate, true);
		} else {
			user.setUpdated(currentDate);
		}
		save(user);
		return user;
	}

	public void save(final User user) {
		bll.save(user);
	}
}