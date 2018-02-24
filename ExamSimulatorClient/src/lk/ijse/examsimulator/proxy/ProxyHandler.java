package lk.ijse.examsimulator.proxy;

import lk.ijse.examsimulator.service.ServiceFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by oshan on 28-Dec-17.
 *
 * @author oshan
 */
public class ProxyHandler {
    private static ProxyHandler proxyHandler;
    private ServiceFactory serviceFactory;

    private ProxyHandler(){

        try {
            serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/ExamServer");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }

    }

    public static ProxyHandler getInstance(){
        if (proxyHandler == null){
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }

    public ServiceFactory getServiceFactory(){
        return serviceFactory;
    }
}
