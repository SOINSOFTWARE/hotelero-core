package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;

import lombok.extern.log4j.Log4j;

import com.soinsoftware.hotelero.persistence.bll.InvoiceStatusBll;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class InvoiceStatusController {

	public InvoiceStatus select(final String name) {
		try {
			final InvoiceStatusBll bll = new InvoiceStatusBll();
			final InvoiceStatus status = bll.select(name);
			bll.closeDbConnection();
			return status;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public InvoiceStatus selectNoPaid() {
		try {
			final InvoiceStatusBll bll = new InvoiceStatusBll();
			final InvoiceStatus status = bll.selectInvoiceStatusNoPaid();
			bll.closeDbConnection();
			return status;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public InvoiceStatus selectPaid() {
		try {
			final InvoiceStatusBll bll = new InvoiceStatusBll();
			final InvoiceStatus status = bll.selectInvoiceStatusPaid();
			bll.closeDbConnection();
			return status;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public InvoiceStatus selectBillToCompany() {
		try {
			final InvoiceStatusBll bll = new InvoiceStatusBll();
			final InvoiceStatus status = bll.selectInvoiceStatusBillToCompany();
			bll.closeDbConnection();
			return status;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public InvoiceStatus selectDeleted() {
		try {
			final InvoiceStatusBll bll = new InvoiceStatusBll();
			final InvoiceStatus status = bll.selectInvoiceStatusDeleted();
			bll.closeDbConnection();
			return status;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public void save(final InvoiceStatus invoiceStatus) {
		try {
			final InvoiceStatusBll bll = new InvoiceStatusBll();
			bll.save(invoiceStatus);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}