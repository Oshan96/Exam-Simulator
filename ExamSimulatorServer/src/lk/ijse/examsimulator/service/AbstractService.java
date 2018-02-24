package lk.ijse.examsimulator.service;

import lk.ijse.examsimulator.observable.ExaminerObservable;
import lk.ijse.examsimulator.observable.StudentObservable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by oshan on 22-Jan-18.
 *
 * @author oshan
 */
public abstract class AbstractService extends UnicastRemoteObject implements SuperService {
    protected static StudentObservable studentObservable=new StudentObservable();
    protected static ExaminerObservable examinerObservable=new ExaminerObservable();

    protected AbstractService()throws RemoteException{

    }

}
