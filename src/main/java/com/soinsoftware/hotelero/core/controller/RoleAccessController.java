package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.RoleAccessBll;
import com.soinsoftware.hotelero.persistence.entity.Role;
import com.soinsoftware.hotelero.persistence.entity.RoleAccess;

import lombok.extern.log4j.Log4j;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class RoleAccessController {

	public List<RoleAccess> select() {
		try {
			final RoleAccessBll bll = new RoleAccessBll();
			final List<RoleAccess> roleAccesses = bll.selectAll(true);
			bll.closeDbConnection();
			return roleAccesses;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public List<RoleAccess> select(final Role role) {
		try {
			final RoleAccessBll bll = new RoleAccessBll();
			final List<RoleAccess> roleAccesses = bll.select(role);
			bll.closeDbConnection();
			return roleAccesses;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final RoleAccess roleAccess) {
		try {
			final RoleAccessBll bll = new RoleAccessBll();
			bll.save(roleAccess);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}