package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.FloorBll;
import com.soinsoftware.hotelero.persistence.entity.Floor;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class FloorController {

	private final FloorBll bll;

	public FloorController() throws IOException {
		super();
		bll = new FloorBll();
	}

	public List<Floor> select() {
		final List<Floor> floors = bll.selectAll(true);
		if (floors != null && !floors.isEmpty()) {
			Collections.sort(floors);
		}
		return floors;
	}

	public void save(final String code, final String name) {
		final Floor floor = new Floor(code, name);
		save(floor);
	}

	public void save(final Floor floor) {
		bll.save(floor);
	}
}
