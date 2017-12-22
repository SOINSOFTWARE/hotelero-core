package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.InvoiceItemBll;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceItem;
import com.soinsoftware.hotelero.persistence.entity.Service;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class InvoiceItemController {

	private final InvoiceItemBll bll;

	public InvoiceItemController() throws IOException {
		super();
		bll = new InvoiceItemBll();
	}

	public List<InvoiceItem> selectByInvoice(final Invoice invoice) {
		final List<InvoiceItem> invoiceItems = bll.select(invoice);
		if (invoiceItems != null && !invoiceItems.isEmpty()) {
			Collections.sort(invoiceItems);
		}
		return invoiceItems;
	}

	public InvoiceItem save(final Invoice invoice, final Service service, final int quantity, final long unitvalue,
			final long value, final Date invoiceItemDate) {
		final Date currentDate = new Date();
		final InvoiceItem invoiceItem = new InvoiceItem(invoice, service, quantity, unitvalue, value, invoiceItemDate,
				currentDate, currentDate, true);
		save(invoiceItem);
		return invoiceItem;
	}

	public void save(final InvoiceItem invoiceItem) {
		bll.save(invoiceItem);
	}
}