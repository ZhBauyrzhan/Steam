package service;import com.j256.ormlite.dao.Dao;

import exception.MyException;
import model.Model;
import model.User;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractService<T extends Model> implements Service<T>{
	private final Dao<T, Integer> dao;
    private final Dao<User, Integer>userDao;
    public AbstractService(Dao<T, Integer> dao, Dao<User, Integer> userDao) {
        this.dao = dao;
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
    @Override
    public Dao<T, Integer> dao() {
        return dao;
    }
}