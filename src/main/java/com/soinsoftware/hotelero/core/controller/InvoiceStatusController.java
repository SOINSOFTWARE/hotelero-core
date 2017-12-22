package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;

import com.soinsoftware.hotelero.persistence.bll.InvoiceStatusBll;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class InvoiceStatusController {

	private final InvoiceStatusBll bll;

	public InvoiceStatusController() throws IOException {
		super();
		bll = new InvoiceStatusBll();
	}

	public InvoiceStatus select(final String name) {
		return bll.select(name);
	}

	public InvoiceStatus selectNoPaid() {
		return bll.selectInvoiceStatusNoPaid();
	}

	public InvoiceStatus selectPaid() {
		return bll.selectInvoiceStatusPaid();
	}

	public InvoiceStatus selectBillToCompany() {
		return bll.selectInvoiceStatusBillToCompany();
	}

	public InvoiceStatus selectDeleted() {
		return bll.selectInvoiceStatusDeleted();
	}

	public void save(final InvoiceStatus invoiceStatus) {
		bll.save(invoiceStatus);
	}
}