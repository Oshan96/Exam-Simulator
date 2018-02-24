package lk.ijse.examsimulator.repository;

import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
public abstract class AbstractRepository<T,ID extends Serializable> implements SuperRepository<T,ID> {

    private Session session;
    private Class<T> typeClass;

    public AbstractRepository() {
        typeClass = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public boolean add(T t) throws Exception {
        return session.save(t)!=null;
    }

    @Override
    public void delete(ID t) throws Exception {
        session.delete(t);
    }

    @Override
    public void update(T t) throws Exception {
        session.update(t);
    }

    @Override
    public T get(ID id) throws Exception {
        return session.get(typeClass,id);
    }

    @Override
    public List<T> getAll() throws Exception {
        return session.createQuery("FROM "+ typeClass.getSimpleName(),typeClass).list();
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}
