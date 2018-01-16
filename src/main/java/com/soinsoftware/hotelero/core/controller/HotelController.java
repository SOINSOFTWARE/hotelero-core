package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.HotelBll;
import com.soinsoftware.hotelero.persistence.entity.Hotel;

import lombok.extern.log4j.Log4j;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class HotelController {

	public List<Hotel> select() {
		try {
			final HotelBll bll = new HotelBll();
			final List<Hotel> hotels = bll.selectAll(true);
			bll.closeDbConnection();
			if (hotels != null && !hotels.isEmpty()) {
				Collections.sort(hotels);
			}
			return hotels;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final String name, final String nit) {
		final Date currentDate = new Date();
		final Hotel hotel = new Hotel(name, nit, currentDate, currentDate, true);
		save(hotel);
	}

	public void save(final Hotel hotel) {
		try {
			final HotelBll bll = new HotelBll();
			bll.save(hotel);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}