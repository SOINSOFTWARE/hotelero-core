package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.RoleBll;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.Role;

import lombok.extern.log4j.Log4j;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class RoleController {

	public List<Role> select() {
		try {
			final RoleBll bll = new RoleBll();
			final List<Role> roles = bll.selectAll(true);
			bll.closeDbConnection();
			if (roles != null && !roles.isEmpty()) {
				Collections.sort(roles);
			}
			return roles;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final String name, final Hotel hotel) {
		final Date currentDate = new Date();
		final Role role = new Role(name, currentDate, currentDate, true, hotel);
		save(role);
	}

	public void save(final Role role) {
		try {
			final RoleBll bll = new RoleBll();
			bll.save(role);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}