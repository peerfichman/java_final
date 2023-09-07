package il.ac.shenkar.model;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    DBConnection conn =  DBConnection.getInstance();
    T get (int id);
    List<T> getAll();
    void save (T t);
    void update(int id, T t);
    void delete(T t);



}
