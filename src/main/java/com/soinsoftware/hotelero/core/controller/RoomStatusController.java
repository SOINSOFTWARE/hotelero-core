package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.List;

import lombok.extern.log4j.Log4j;

import com.soinsoftware.hotelero.persistence.bll.RoomStatusBll;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class RoomStatusController {

	public List<RoomStatus> select() {
		try {
			final RoomStatusBll bll = new RoomStatusBll();
			final List<RoomStatus> statuses = bll.selectAll(true);
			bll.closeDbConnection();
			return statuses;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public RoomStatus selectBooked() {
		try {
			final RoomStatusBll bll = new RoomStatusBll();
			final RoomStatus status = bll.selectRoomStatusBooked();
			bll.closeDbConnection();
			return status;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public RoomStatus selectEnabled() {
		try {
			final RoomStatusBll bll = new RoomStatusBll();
			final RoomStatus status = bll.selectRoomStatusEnabled();
			bll.closeDbConnection();
			return status;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public RoomStatus selectDisabled() {
		try {
			final RoomStatusBll bll = new RoomStatusBll();
			final RoomStatus status = bll.selectRoomStatusDisabled();
			bll.closeDbConnection();
			return status;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final RoomStatus roomStatus) {
		try {
			final RoomStatusBll bll = new RoomStatusBll();
			bll.save(roomStatus);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}