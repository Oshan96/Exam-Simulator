package lk.ijse.examsimulator.service.custom.impl;

import lk.ijse.examsimulator.business.BOFactory;
import lk.ijse.examsimulator.business.custom.ExaminerBO;
import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.observable.StudentObservable;
import lk.ijse.examsimulator.observer.StudentObserver;
import lk.ijse.examsimulator.service.AbstractService;
import lk.ijse.examsimulator.service.custom.ExaminerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class ExaminerServiceImpl extends AbstractService implements ExaminerService {

    private ExaminerBO examinerBO;

    public ExaminerServiceImpl() throws RemoteException {
        examinerBO= (ExaminerBO) BOFactory.getInstance().getBO(BOFactory.BOType.EXAMINER);
    }


    @Override
    public void addStudentObserver(StudentObserver observer) throws Exception {
        studentObservable.addObserver(observer);

    }

    @Override
    public void removeStudentObserver(StudentObserver observer) throws Exception {
        studentObservable.removeObserver(observer);
        examinerObservable.notifyStudentListUpdate();
    }

    @Override
    public void requestScreenShare(String student) throws Exception {
        studentObservable.requestScreenShare(student);
    }

    @Override
    public ArrayList<StudentObserver> getStudents() throws Exception {
        return studentObservable.getObservers();
    }

    @Override
    public void uploadPaper(Paper paper) throws Exception {
        examinerBO.uploadPaper(paper);
    }

    @Override
    public Paper getPaper() throws Exception {
        return examinerBO.getPaper();
    }

    @Override
    public boolean login(String username, String password) throws Exception {
        return examinerBO.login(username,password);
    }
}
