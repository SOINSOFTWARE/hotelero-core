package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.RoomStatusBll;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class RoomStatusController {

	private final RoomStatusBll bll;

	public RoomStatusController() throws IOException {
		super();
		bll = new RoomStatusBll();
	}

	public List<RoomStatus> select() {
		return bll.selectAll(true);
	}

	public RoomStatus selectBooked() {
		return bll.selectRoomStatusBooked();
	}

	public RoomStatus selectEnabled() {
		return bll.selectRoomStatusEnabled();
	}

	public RoomStatus selectDisabled() {
		return bll.selectRoomStatusDisabled();
	}

	public void save(final RoomStatus roomStatus) {
		bll.save(roomStatus);
	}
}