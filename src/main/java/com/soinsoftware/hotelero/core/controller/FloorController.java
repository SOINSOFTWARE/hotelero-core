package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.FloorBll;
import com.soinsoftware.hotelero.persistence.entity.Floor;
import com.soinsoftware.hotelero.persistence.entity.Hotel;

import lombok.extern.log4j.Log4j;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class FloorController {

	public List<Floor> select() {
		try {
			final FloorBll bll = new FloorBll();
			final List<Floor> floors = bll.selectAll(true);
			bll.closeDbConnection();
			if (floors != null && !floors.isEmpty()) {
				Collections.sort(floors);
			}
			return floors;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final String code, final String name, final Hotel hotel) {
		final Floor floor = new Floor(code, name, hotel);
		save(floor);
	}

	public void save(final Floor floor) {
		try {
			final FloorBll bll = new FloorBll();
			bll.save(floor);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}