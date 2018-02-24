package lk.ijse.examsimulator.repository;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
public interface SuperRepository<T,ID extends Serializable> {
    boolean add(T t) throws Exception;
    void delete(ID t) throws Exception;
    void update(T t)throws Exception;
    T get(ID id) throws Exception;
    List<T> getAll() throws Exception;
    void setSession(Session session);
}
