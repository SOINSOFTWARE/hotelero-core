/**
 * 
 */
package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.ServiceTypeBll;
import com.soinsoftware.hotelero.persistence.entity.ServiceType;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class ServiceTypeController {

	private final ServiceTypeBll bll;

	public ServiceTypeController() throws IOException {
		super();
		bll = new ServiceTypeBll();
	}

	public List<ServiceType> select() {
		final List<ServiceType> serviceTypes = bll.selectAll(true);
		if (serviceTypes != null && !serviceTypes.isEmpty()) {
			Collections.sort(serviceTypes);
		}
		return serviceTypes;
	}

	public void save(final String name) {
		final Date currentDate = new Date();
		final ServiceType serviceType = new ServiceType(name, currentDate, currentDate, true);
		save(serviceType);
	}

	public void save(final ServiceType serviceType) {
		bll.save(serviceType);
	}
}