package lk.ijse.examsimulator.observer;

/**
 * Created by oshan on 28-Dec-17.
 *
 * @author oshan
 */
public interface ExaminerObserver extends SuperObserver{
    void notifyRestrictedAppBehaviour(String studentName) throws Exception;
    void removeExaminerObserver() throws Exception;
    void informStudentLogin(String studentId) throws Exception;
    void streamScreen(byte[] imageStream) throws Exception;
    void notifyUIUpdate() throws Exception;
}
