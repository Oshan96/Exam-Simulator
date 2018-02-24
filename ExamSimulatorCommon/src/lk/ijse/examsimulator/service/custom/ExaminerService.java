package lk.ijse.examsimulator.service.custom;

import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.observer.StudentObserver;
import lk.ijse.examsimulator.service.SuperService;

import java.util.ArrayList;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public interface ExaminerService extends SuperService {
    void addStudentObserver(StudentObserver observer)throws Exception;
    void removeStudentObserver(StudentObserver observer)throws Exception;
    void requestScreenShare(String student) throws Exception;
    ArrayList<StudentObserver> getStudents() throws Exception;
    void uploadPaper(Paper paper)throws Exception;
    Paper getPaper()throws Exception;
}
