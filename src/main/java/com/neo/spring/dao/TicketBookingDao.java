package com.neo.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketBookingDao {

	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public int getAccountId(int userId) throws SQLException {
		int accountId = 0;
		String query = "select Account_Id from User_Table where user_Id= " + userId;
		Statement statement;
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			accountId = resultSet.getInt(1);
		}
		return accountId;
	}

	public float getPrice(int ticketId) throws SQLException {
		float price = 0;
		String query = "select Ticket_Price from Movie_Ticket where Ticket_Id= " + ticketId;
		Statement statement;
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		if (resultSet.next()) {
			price = resultSet.getFloat(1);
		}
		return price;
	}

	public void deductAmount(int accountId, double amount) throws SQLException {

		String query = "Update Account SET BAL= BAL-" + amount + "where AccNo=" + accountId;
		Statement statement;
		statement = connection.createStatement();
		statement.executeUpdate(query);
	}

	public void reduceTicketCount(int ticketId, int noOfTickets) throws SQLException {

		String query = "Update Movie_Ticket SET Ticket_Count= Ticket_Count-" + noOfTickets + "where Ticket_Id="
				+ ticketId;
		Statement statement;
		statement = connection.createStatement();
		statement.executeUpdate(query);
	}

}
