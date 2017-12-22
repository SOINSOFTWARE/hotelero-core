/**
 * 
 */
package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.ServiceBll;
import com.soinsoftware.hotelero.persistence.entity.Service;
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class ServiceController {

	private final ServiceBll bll;

	public ServiceController() throws IOException {
		super();
		bll = new ServiceBll();
	}

	public List<Service> select() {
		final List<Service> services = bll.selectAll(true);
		if (services != null && !services.isEmpty()) {
			Collections.sort(services);
		}
		return services;
	}

	public List<Service> selectByServiceType(final ServiceType serviceType) {
		final List<Service> services = bll.selectByServiceType(serviceType);
		if (services != null && !services.isEmpty()) {
			Collections.sort(services);
		}
		return services;
	}

	public void save(final ServiceType serviceType, final String name, final long value) {
		final Date currentDate = new Date();
		final Service service = new Service(serviceType, name, value, currentDate, currentDate, true);
		save(service);
	}

	public void save(final Service service) {
		bll.save(service);
	}
}