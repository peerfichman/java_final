package il.ac.shenkar.model;

import java.util.List;

public interface DAO<T>  {
    T get (int id) throws Exception;
    List<T> getAll() throws Exception;
    void save (T t) throws Exception;
    void update(int id, T t) throws Exception;
    void delete(T t) throws Exception;



}
