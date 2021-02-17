package configuration;

import java.sql.SQLException;


import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import exception.MyException;
import model.Game;
import model.User;
import model.UsersGames;

public class JdbcDataBaseConfiguration implements DataBaseConfiguration{
	private final ConnectionSource connectionSource;
	public JdbcDataBaseConfiguration(String jdbConnectionSource) {
		try {
			connectionSource = new JdbcConnectionSource(jdbConnectionSource);
			TableUtils.createTableIfNotExists(connectionSource, User.class);
			TableUtils.createTableIfNotExists(connectionSource, Game.class);
			TableUtils.createTableIfNotExists(connectionSource, UsersGames.class);
		} catch(SQLException e){
			e.printStackTrace();
            throw new MyException("Can't initialize database connection",  e); 
		}
	}
	@Override
	public ConnectionSource connectionSource() {
		return connectionSource;
	}
}