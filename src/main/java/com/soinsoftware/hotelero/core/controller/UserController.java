package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Date;

import com.soinsoftware.hotelero.persistence.bll.UserBll;
import com.soinsoftware.hotelero.persistence.entity.Company;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.User;

import lombok.extern.log4j.Log4j;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class UserController {

	public boolean isExistingUser(final long identification) {
		return (select(identification) != null);
	}

	public User select(final long identification) {
		try {
			final UserBll bll = new UserBll();
			final User user = bll.select(identification);
			bll.closeDbConnection();
			return user;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public User select(final String login, final char[] password) {
		try {
			final UserBll bll = new UserBll();
			final String strPassword = String.valueOf(password);
			final User user = bll.select(login, strPassword);
			bll.closeDbConnection();
			return user;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public User save(final Company company, final long identification, final String name, final long phone,
			final String career, final Hotel hotel) {
		User user = select(identification);
		final Date currentDate = new Date();
		if (user == null) {
			user = new User(company, identification, name, phone, career, currentDate, currentDate, true, hotel);
		} else {
			user.setUpdated(currentDate);
		}
		save(user);
		return user;
	}

	public void save(final User user) {
		try {
			final UserBll bll = new UserBll();
			bll.save(user);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}