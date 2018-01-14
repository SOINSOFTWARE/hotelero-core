package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import lombok.extern.log4j.Log4j;

import com.soinsoftware.hotelero.persistence.bll.RoomTypeBll;
import com.soinsoftware.hotelero.persistence.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class RoomTypeController {

	public List<RoomType> select() {
		try {
			final RoomTypeBll bll = new RoomTypeBll();
			final List<RoomType> roomTypes = bll.selectAll(true);
			bll.closeDbConnection();
			if (roomTypes != null && !roomTypes.isEmpty()) {
				Collections.sort(roomTypes);
			}
			return roomTypes;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final String code, final String name) {
		final RoomType roomType = new RoomType(code, name);
		save(roomType);
	}

	public void save(final RoomType roomType) {
		try {
			final RoomTypeBll bll = new RoomTypeBll();
			bll.save(roomType);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}
