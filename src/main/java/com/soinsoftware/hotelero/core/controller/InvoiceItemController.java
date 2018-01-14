package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.extern.log4j.Log4j;

import com.soinsoftware.hotelero.persistence.bll.InvoiceItemBll;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceItem;
import com.soinsoftware.hotelero.persistence.entity.Service;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
@Log4j
public class InvoiceItemController {

	public List<InvoiceItem> selectByInvoice(final Invoice invoice) {
		try {
			final InvoiceItemBll bll = new InvoiceItemBll();
			final List<InvoiceItem> invoiceItems = bll.select(invoice);
			bll.closeDbConnection();
			if (invoiceItems != null && !invoiceItems.isEmpty()) {
				Collections.sort(invoiceItems);
			}
			return invoiceItems;
		} catch (final IOException ex) {
			log.error(ex.getMessage());
			return null;
		}
	}

	public InvoiceItem save(final Invoice invoice, final Service service,
			final int quantity, final long unitvalue, final long value,
			final Date invoiceItemDate) {
		final Date currentDate = new Date();
		final InvoiceItem invoiceItem = new InvoiceItem(invoice, service,
				quantity, unitvalue, value, invoiceItemDate, currentDate,
				currentDate, true);
		save(invoiceItem);
		return invoiceItem;
	}

	public void save(final InvoiceItem invoiceItem) {
		try {
			final InvoiceItemBll bll = new InvoiceItemBll();
			bll.save(invoiceItem);
			bll.closeDbConnection();
		} catch (final IOException ex) {
			log.error(ex.getMessage());
		}
	}
}