package lk.ijse.examsimulator.observable;

import lk.ijse.examsimulator.observer.SuperObserver;

/**
 * Created by oshan on 28-Dec-17.
 *
 * @author oshan
 */
public interface SuperObservable<T extends SuperObserver>{
    void addObserver(T t) throws Exception;
    void removeObserver(T t) throws Exception;
}