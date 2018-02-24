package lk.ijse.examsimulator.observer.impl;

import lk.ijse.examsimulator.observer.ExaminerObserver;
import lk.ijse.examsimulator.proxy.ProxyHandler;
import lk.ijse.examsimulator.service.ServiceFactory;
import lk.ijse.examsimulator.service.custom.StudentService;
import lk.ijse.examsimulator.ui.controller.Application;

import java.rmi.server.UnicastRemoteObject;

/**
 * Created by oshan on 28-Dec-17.
 *
 * @author oshan
 */
public class ExaminerObserverImpl extends UnicastRemoteObject implements ExaminerObserver {

    private Application controller;
    private StudentService studentService;

    public ExaminerObserverImpl(Application controller) throws Exception {

        this.controller = controller;
        studentService= (StudentService) ProxyHandler.getInstance().getServiceFactory().getService(ServiceFactory.ServiceType.STUDENT);

        studentService.addExaminerObserver(this);
    }

    @Override
    public void notifyRestrictedAppBehaviour(String studentId) throws Exception {
        controller.notifyRestrictedAppBehaviour(studentId);
    }

    @Override
    public void removeExaminerObserver() throws Exception{
        studentService.removeExaminerObserver(this);
    }

    @Override
    public void informStudentLogin(String studentId) throws Exception {
        controller.informStudentLogin(studentId);
    }

    @Override
    public void streamScreen(byte[] imageStream) throws Exception {
        controller.streamStudentScreen(imageStream);
    }

    @Override
    public void notifyUIUpdate() throws Exception {
        controller.updateLoggedStudents();
    }


}
