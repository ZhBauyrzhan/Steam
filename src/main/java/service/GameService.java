package service;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import exception.MyException;
import model.Game;
import model.User;

public class GameService extends AbstractService<Game>{
	public GameService(Dao<Game, Integer> dao, Dao<User, Integer> userDao) {
        super(dao, userDao);
    }
}