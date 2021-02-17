package service;import com.j256.ormlite.dao.Dao;

import model.Model;

public abstract class AbstractService<T extends Model> implements Service<T>{
	private final Dao<T, Integer> dao;

    public AbstractService(Dao<T, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public Dao<T, Integer> dao() {
        return dao;
    }
}