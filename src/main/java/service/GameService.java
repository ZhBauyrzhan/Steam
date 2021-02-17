package service;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import exception.MyException;
import model.Game;
import model.User;

public class GameService extends AbstractService<Game>{
	private final Dao<User, Integer> userDao;
	public GameService(Dao<Game, Integer> dao, Dao<User, Integer> userDao) {
        super(dao);
        this.userDao = userDao;
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