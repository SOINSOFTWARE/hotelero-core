package com.soinsoftware.hotelero.core.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.soinsoftware.hotelero.persistence.bll.InvoiceBll;
import com.soinsoftware.hotelero.persistence.entity.Company;
import com.soinsoftware.hotelero.persistence.entity.Invoice;
import com.soinsoftware.hotelero.persistence.entity.InvoiceStatus;
import com.soinsoftware.hotelero.persistence.entity.Room;
import com.soinsoftware.hotelero.persistence.entity.RoomStatus;
import com.soinsoftware.hotelero.persistence.entity.User;

/**
 * @author Carlos Rodriguez
 * @since 1.0.0
 */
public class InvoiceController {

	private final InvoiceBll bll;

	private final InvoiceStatusController invoiceStatusController;

	private final RoomController roomController;

	private final RoomStatusController roomStatusController;

	public InvoiceController() throws IOException {
		super();
		bll = new InvoiceBll();
		invoiceStatusController = new InvoiceStatusController();
		roomController = new RoomController();
		roomStatusController = new RoomStatusController();
	}

	public List<Invoice> selectBooked() {
		final RoomStatus roomStatus = roomStatusController.selectBooked();
		final Date initialDate = getInitialDateForBooked();
		final List<Invoice> invoices = bll.selectByStatus(roomStatus, initialDate);
		if (invoices != null && !invoices.isEmpty()) {
			Collections.sort(invoices);
		}
		return invoices;
	}

	public List<Invoice> selectNotEnabled(final Date initialDate, final Date finalDate) {
		buildDateWithHour(initialDate, 12);
		buildDateWithHour(finalDate, 12);
		final RoomStatus roomStatus = roomStatusController.selectEnabled();
		return bll.selectByNonStatus(roomStatus, initialDate, finalDate);
	}

	public List<Invoice> selectNotEnabled() {
		final RoomStatus roomStatus = roomStatusController.selectDisabled();
		final List<Invoice> invoices = bll.selectByStatus(roomStatus);
		if (invoices != null && !invoices.isEmpty()) {
			Collections.sort(invoices, new Comparator<Invoice>() {

				@Override
				public int compare(final Invoice firstInvoice, final Invoice secondInvoice) {
					final Room firstRoom = firstInvoice.getRoom();
					final Room secondRoom = secondInvoice.getRoom();
					return firstRoom.getName().compareTo(secondRoom.getName());
				}
			});
		}
		return invoices;
	}

	public List<Invoice> selectByDate(final int year, final int month, final InvoiceStatus invoiceStatus,
			final Company company) {
		final RoomStatus roomStatusEnabled = roomStatusController.selectEnabled();
		final List<Invoice> invoices = bll.selectByStatus(roomStatusEnabled, year, month, invoiceStatus, company);
		if (invoices != null && !invoices.isEmpty()) {
			Collections.sort(invoices);
		}
		return invoices;
	}

	public Invoice saveBooking(final User user, final String roomName, final Date initialDate, final Date finalDate,
			final String siteFrom, final String siteTo, final Company company) {
		final RoomStatus roomStatus = roomStatusController.selectBooked();
		return save(user, roomName, roomStatus, initialDate, finalDate, siteFrom, siteTo, company);
	}

	public Invoice saveCheckIn(final User user, final String roomName, final Date initialDate, final Date finalDate,
			final String siteFrom, final String siteTo, final Company company) {
		final RoomStatus roomStatus = roomStatusController.selectDisabled();
		return save(user, roomName, roomStatus, initialDate, finalDate, siteFrom, siteTo, company);
	}

	private Invoice save(final User user, final String roomCode, final RoomStatus roomStatus, final Date initialDate,
			final Date finalDate, final String siteFrom, final String siteTo, final Company company) {
		buildDateWithHour(initialDate, 13);
		buildDateWithHour(finalDate, 12);
		final Room room = roomController.select(roomCode);
		final InvoiceStatus invoiceStatus = invoiceStatusController.selectNoPaid();
		final Date currentDate = new Date();
		final Invoice invoice = new Invoice(company, invoiceStatus, room, roomStatus, user, initialDate, finalDate, 0,
				siteFrom, siteTo, currentDate, currentDate, true, null);
		this.save(invoice);
		return invoice;
	}

	public void save(final Invoice invoice) {
		bll.save(invoice);
	}

	private void buildDateWithHour(Date date, final int hourOfday) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hourOfday);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		date = cal.getTime();
	}

	private Date getInitialDateForBooked() {
		final LocalTime midnight = LocalTime.MIDNIGHT;
		final LocalDate today = LocalDate.now();
		final LocalDateTime tomorrowMidnight = today.plusDays(1).atTime(midnight);
		final ZonedDateTime zdt = tomorrowMidnight.atZone(ZoneId.systemDefault());
		return Date.from(zdt.toInstant());
	}
}