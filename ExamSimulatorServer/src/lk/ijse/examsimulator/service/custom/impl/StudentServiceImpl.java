package lk.ijse.examsimulator.service.custom.impl;

import lk.ijse.examsimulator.business.BOFactory;
import lk.ijse.examsimulator.business.custom.StudentBO;
import lk.ijse.examsimulator.observable.ExaminerObservable;
import lk.ijse.examsimulator.observer.ExaminerObserver;
import lk.ijse.examsimulator.service.AbstractService;
import lk.ijse.examsimulator.service.custom.StudentService;
import lk.ijse.examsimulator.util.executable.custom.Executable;
import lk.ijse.examsimulator.util.executable.custom.impl.ExecutableImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class StudentServiceImpl extends AbstractService implements StudentService {
    private StudentBO studentBO;

    public StudentServiceImpl() throws RemoteException {
        studentBO= (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.STUDENT);
    }

    @Override
    public void notifyRestrictedAppBehaviour(String studentId) throws Exception {
        examinerObservable.notifyRestrictedAppBehaviour(studentId);
    }

    @Override
    public void addExaminerObserver(ExaminerObserver observer) throws Exception {
        examinerObservable.addObserver(observer);
    }

    @Override
    public void removeExaminerObserver(ExaminerObserver observer) throws Exception {
        examinerObservable.removeObserver(observer);
    }

    @Override
    public void streamScreen(byte[] imageStream) throws Exception {
        examinerObservable.streamScreen(imageStream);
    }

    @Override
    public void informStudentLogin(String studentId) throws Exception {
        examinerObservable.informStudentLogin(studentId);
    }

    @Override
    public String getOutput(String source) throws Exception {
        Executable executable=new ExecutableImpl();
        return executable.getOutputFromSource(source,"Demo");
    }

    @Override
    public boolean login(String username, String password) throws Exception {
        return studentBO.login(username,password);
    }
}
