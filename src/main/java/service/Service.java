package service;

import java.util.List;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import exception.MyException;
import model.Model;
import model.User;


public interface Service<T extends Model> {
	Dao<T,Integer> dao();
	default List<T> findAll(int pageNumber, int pageSize) {
		try {
			try {
				QueryBuilder queryBuilder = dao().queryBuilder().offset((long) pageNumber * pageSize).limit((long) pageSize);
				return queryBuilder.query();
			} catch (SQLException e) {
				throw new MyException(e.getMessage());
			}
		} catch (Exception e) {
			throw new MyException(e.getMessage());
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