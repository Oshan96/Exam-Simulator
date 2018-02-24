package lk.ijse.examsimulator.service;

import java.rmi.Remote;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public interface SuperService extends Remote {
    boolean login(String username,String password) throws Exception;
}
