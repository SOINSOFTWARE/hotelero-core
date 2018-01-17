/**
 * 
 */
package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.ServiceBll;
import com.soinsoftware.hotelero.persistence.entity.Hotel;
import com.soinsoftware.hotelero.persistence.entity.Service;
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

import lombok.extern.log4j.Log4j;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class ServiceController {

	public List<Service> select() {
		try {
			final ServiceBll bll = new ServiceBll();
			final List<Service> services = bll.selectAll(true);
			bll.closeDbConnection();
			if (services != null && !services.isEmpty()) {
				Collections.sort(services);
			}
			return services;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public List<Service> selectByServiceType(final ServiceType serviceType) {
		try {
			final ServiceBll bll = new ServiceBll();
			final List<Service> services = bll.selectByServiceType(serviceType);
			bll.closeDbConnection();
			if (services != null && !services.isEmpty()) {
				Collections.sort(services);
			}
			return services;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final ServiceType serviceType, final String name, final long value, final Hotel hotel) {
		final Date currentDate = new Date();
		final Service service = new Service(serviceType, name, value, currentDate, currentDate, true, hotel);
		save(service);
	}

	public void save(final Service service) {
		try {
			final ServiceBll bll = new ServiceBll();
			bll.save(service);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}