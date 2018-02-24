package lk.ijse.examsimulator.service;

import java.rmi.Remote;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public interface ServiceFactory extends Remote {
    enum ServiceType{
        EXAMINER,STUDENT,EXECUTABLE
    }

    SuperService getService(ServiceType type) throws Exception;
}
