package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.RoomTypeBll;
import com.soinsoftware.hotelero.persistence.entity.RoomType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoomTypeController {

	private final RoomTypeBll bll;

	public RoomTypeController() throws IOException {
		super();
		bll = new RoomTypeBll();
	}

	public List<RoomType> select() {
		final List<RoomType> roomTypes = bll.selectAll(true);
		if (roomTypes != null && !roomTypes.isEmpty()) {
			Collections.sort(roomTypes);
		}
		return roomTypes;
	}

	public void save(final String code, final String name) {
		final RoomType roomType = new RoomType(code, name);
		save(roomType);
	}

	public void save(final RoomType roomType) {
		bll.save(roomType);
	}
}
