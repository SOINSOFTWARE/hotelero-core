package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import lombok.extern.log4j.Log4j;

import com.soinsoftware.hotelero.persistence.bll.RoomBll;
import com.soinsoftware.hotelero.persistence.entity.Room;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class RoomController {

	public List<Room> select() {
		try {
			final RoomBll bll = new RoomBll();
			final List<Room> rooms = bll.selectAll(true);
			if (rooms != null && !rooms.isEmpty()) {
				Collections.sort(rooms);
			}
			bll.closeDbConnection();
			return rooms;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public Room select(final String code) {
		try {
			final RoomBll bll = new RoomBll();
			final Room room = bll.select(code);
			bll.closeDbConnection();
			return room;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final Room room) {
		try {
			final RoomBll bll = new RoomBll();
			bll.save(room);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}