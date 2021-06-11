package Service;

import java.util.List;

/**
 *
 * @author Firas
 */
public interface IService_T <T>{
    void insert(T t);
    boolean update(T t);
    boolean delete(int id);
    List<T> displayAll();
    
}