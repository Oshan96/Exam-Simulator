package lk.ijse.examsimulator.main;

import lk.ijse.examsimulator.service.impl.ServiceFactoryImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class ServerStart {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname","localhost");
            Registry registry= LocateRegistry.createRegistry(5050);
            registry.rebind("ExamServer", ServiceFactoryImpl.getInstance());
            System.out.println("Server started...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
