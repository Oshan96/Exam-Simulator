package lk.ijse.examsimulator.service.impl;

import lk.ijse.examsimulator.service.ServiceFactory;
import lk.ijse.examsimulator.service.SuperService;
import lk.ijse.examsimulator.service.custom.impl.ExaminerServiceImpl;
import lk.ijse.examsimulator.service.custom.impl.StudentServiceImpl;
import lk.ijse.examsimulator.util.executable.custom.impl.ExecutableImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory{
    private static ServiceFactoryImpl factory;

    private ServiceFactoryImpl() throws RemoteException{

    }

    public static ServiceFactoryImpl getInstance() throws RemoteException{
        if(factory==null)factory=new ServiceFactoryImpl();
        return factory;
    }

    @Override
    public SuperService getService(ServiceType type) throws Exception {
        switch (type){
            case STUDENT: return new StudentServiceImpl();
            case EXAMINER: return new ExaminerServiceImpl();
            default:return null;
        }
    }
}
