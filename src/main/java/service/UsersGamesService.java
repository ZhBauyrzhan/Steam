package service;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import exception.MyException;
import model.Game;
import model.User;
import model.UsersGames;

public class UsersGamesService extends AbstractService<UsersGames>{
	private final Dao<User, Integer> userDao;
	private final Dao<Game, Integer> gameDao;
	public UsersGamesService(Dao<UsersGames, Integer> dao, Dao<User, Integer> userDao, Dao<Game,Integer> gameDao) {
		super(dao, userDao);
		this.gameDao = gameDao;
		this.userDao = userDao;
	}
	public User findUserById(int id) {
		try {
			return userDao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQL ex", e);
		}
	}
	public Game findGameById(int id) {
		try {
			return gameDao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQL ex", e);
		}
	}
}