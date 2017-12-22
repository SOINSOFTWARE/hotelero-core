package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.RoomBll;
import com.soinsoftware.hotelero.persistence.entity.Room;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoomController {

	private final RoomBll bll;

	public RoomController() throws IOException {
		super();
		bll = new RoomBll();
	}

	public List<Room> select() {
		final List<Room> rooms = bll.selectAll(true);
		if (rooms != null && !rooms.isEmpty()) {
			Collections.sort(rooms);
		}
		return rooms;
	}

	public Room select(final String code) {
		return bll.select(code);
	}

	public void save(final Room room) {
		bll.save(room);
	}
}