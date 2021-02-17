package service;

import java.util.List;

import com.j256.ormlite.dao.Dao;

import model.User;

public class UserService extends AbstractService<User>{
	public UserService(Dao<User, Integer> userDao) {
		super(userDao);
	}
}