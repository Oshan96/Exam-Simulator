package lk.ijse.examsimulator.service.custom;

import lk.ijse.examsimulator.observer.ExaminerObserver;
import lk.ijse.examsimulator.service.SuperService;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public interface StudentService extends SuperService {
    void notifyRestrictedAppBehaviour(String studentId) throws Exception;

    void addExaminerObserver(ExaminerObserver observer) throws Exception;
    void removeExaminerObserver(ExaminerObserver observer) throws Exception;
    void streamScreen(byte[] imageStream) throws Exception;
    void informStudentLogin(String studentId)throws Exception;
    String getOutput(String source) throws Exception;

}
