package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.CompanyBll;
import com.soinsoftware.hotelero.persistence.entity.Company;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class CompanyController {

	private final CompanyBll bll;

	public CompanyController() throws IOException {
		super();
		bll = new CompanyBll();
	}

	public List<Company> select() {
		final List<Company> companies = bll.selectAll(true);
		if (companies != null && !companies.isEmpty()) {
			Collections.sort(companies);
		}
		return companies;
	}

	public void save(final String name, final String nit) {
		final Date currentDate = new Date();
		final Company serviceType = new Company(name, nit, currentDate, currentDate, true);
		save(serviceType);
	}

	public void save(final Company company) {
		bll.save(company);
	}
}