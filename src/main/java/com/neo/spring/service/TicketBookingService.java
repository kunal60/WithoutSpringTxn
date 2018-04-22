package com.neo.spring.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.neo.spring.dao.TicketBookingDao;

public class TicketBookingService {

	private TicketBookingDao dao;

	private DataSource dataSource;

	public void setDao(TicketBookingDao dao) {
		this.dao = dao;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void bookTicket(int userId, int ticketId, int totalNoTickets) {
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			dao.setConnection(connection);
			int accountId = dao.getAccountId(userId);
			float price = dao.getPrice(ticketId);
			double totalAmount = price * totalNoTickets;
			dao.deductAmount(accountId, totalAmount);
			dao.reduceTicketCount(ticketId, totalNoTickets);

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("......" + e.getMessage());
		}
	}

}
