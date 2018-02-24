package lk.ijse.examsimulator.observer;

import lk.ijse.examsimulator.dto.Paper;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public interface StudentObserver extends SuperObserver{
    String getStudent() throws Exception;
    void startSreenSharing()throws Exception;
    void stopScreenSharing() throws Exception;
    void removeStudentObserver(StudentObserver ob) throws Exception;
    Paper getPaper() throws Exception;
}
