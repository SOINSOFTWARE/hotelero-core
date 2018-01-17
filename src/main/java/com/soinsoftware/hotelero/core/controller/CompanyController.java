package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.CompanyBll;
import com.soinsoftware.hotelero.persistence.entity.Company;
import com.soinsoftware.hotelero.persistence.entity.Hotel;

import lombok.extern.log4j.Log4j;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class CompanyController {

	public List<Company> select() {
		try {
			final CompanyBll bll = new CompanyBll();
			final List<Company> companies = bll.selectAll(true);
			bll.closeDbConnection();
			if (companies != null && !companies.isEmpty()) {
				Collections.sort(companies);
			}
			return companies;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final String name, final String nit, final Hotel hotel) {
		final Date currentDate = new Date();
		final Company serviceType = new Company(name, nit, currentDate, currentDate, true, hotel);
		save(serviceType);
	}

	public void save(final Company company) {
		try {
			final CompanyBll bll = new CompanyBll();
			bll.save(company);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}