/**
 * 
 */
package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.ServiceTypeBll;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

import lombok.extern.log4j.Log4j;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class ServiceTypeController {

	public List<ServiceType> select() {
		try {
			final ServiceTypeBll bll = new ServiceTypeBll();
			final List<ServiceType> serviceTypes = bll.selectAll(true);
			bll.closeDbConnection();
			if (serviceTypes != null && !serviceTypes.isEmpty()) {
				Collections.sort(serviceTypes);
			}
			return serviceTypes;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final String name, final Hotel hotel) {
		final Date currentDate = new Date();
		final ServiceType serviceType = new ServiceType(name, currentDate, currentDate, true, hotel);
		save(serviceType);
	}

	public void save(final ServiceType serviceType) {
		try {
			final ServiceTypeBll bll = new ServiceTypeBll();
			bll.save(serviceType);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}