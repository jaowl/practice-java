package ru.fedinskiy.students.models.dao;

import ru.fedinskiy.students.exception.UserDAOException;
import ru.fedinskiy.students.models.connectors.Connector;
import ru.fedinskiy.students.models.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fedinskiy on 23.02.17.
 */
@Component
public class UserDAO {
	private static Logger logger = LogManager.getLogger(UserDAO.class);
	private static final String SQL_FIND_USER = "SELECT * FROM main.users where login=? and password=?";
	private static final String SQL_ADD_USER = "INSERT INTO main.users( login, password, role)" +
			"VALUES ( ?, ?, ?) RETURNING id ";
	
	public User getUserByLoginAndPassword(String login, String password) throws UserDAOException {
		User user = new User();
		try (Connection conn = Connector.getConnection()) {
			logger.trace("gettingUser");
			System.out.println("getting user");
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_USER);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				user.setIdUser(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(resultSet.getString("role"));
			}else{
				logger.debug(login + " " + password + " not found");
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new UserDAOException();
		}
		return user;
	}
	
	public User registrationUser(String login, String password) {
		User user = new User();
		try (Connection conn = Connector.getConnection()) {
			logger.trace("regging User");
			PreparedStatement ps = conn.prepareStatement(SQL_ADD_USER);
			ps.setString(1, login);
			ps.setString(2, password);
			ps.setString(3, "user");
			final ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				user.setIdUser(resultSet.getInt("id"));
			} else {
				logger.debug(login + " " + password + " not found");
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return user;
	}
}
