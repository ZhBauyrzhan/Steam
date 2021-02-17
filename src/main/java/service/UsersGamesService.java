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
		super(dao);
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
	public User findUserByLogin(String login) throws RuntimeException{
		try {
			List<User> users = userDao.queryForAll();
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).getLogin().equals(login)) {
					return users.get(i);
				}
			}	
			throw new RuntimeException("Нет такого юзера");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQL ex", e);
		}
	}
}