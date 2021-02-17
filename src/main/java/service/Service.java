package service;

import java.util.List;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import exception.MyException;
import model.Model;

public interface Service<T extends Model> {
	Dao<T,Integer> dao();
	
	default List<T> findAll() {
		try {
			return dao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQL ex", e);
		}
	}
	default List<T> findBy(String columnName, Object value) {
		try {
			return dao().queryForEq(columnName, value);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQL ex", e);
		}
	}
	
	default T findById(int id) {
		try {
			return dao().queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQL ex", e);
		}
	}

    default void save(T model) {
        try {
            dao().create(model);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("SQL ex", e);
        }
    }
    
	default void update(T model) {
        try {
            dao().update(model);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("SQL ex", e);
        }
    }

    default void delete (T model) {
        try {
            dao().delete(model);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("SQL ex", e);
        }
    }
	
}