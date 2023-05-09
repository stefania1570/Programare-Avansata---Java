package classes;

import models.Model;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T extends Model> {
    void create(T model) throws SQLException;
    List<T> findAll() throws SQLException;
     T findByName(String name) throws SQLException;
     T findById(int id) throws SQLException;
}
