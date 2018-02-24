package lk.ijse.examsimulator.observer.impl;

import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.observer.StudentObserver;
import lk.ijse.examsimulator.proxy.ProxyHandler;
import lk.ijse.examsimulator.service.ServiceFactory;
import lk.ijse.examsimulator.service.custom.ExaminerService;
import lk.ijse.examsimulator.ui.controller.StudentUI;

import java.rmi.server.UnicastRemoteObject;

/**
 * Created by oshan on 01-Jan-18.
 *
 * @author oshan
 */
public class StudentObserverImpl extends UnicastRemoteObject implements StudentObserver {

    private StudentUI controller;
    private ExaminerService examinerService;

    public StudentObserverImpl(StudentUI controller) throws Exception {
        this.controller=controller;
        examinerService=(ExaminerService) ProxyHandler.getInstance().getServiceFactory().getService(ServiceFactory.ServiceType.EXAMINER);
        examinerService.addStudentObserver(this);
    }

    @Override
    public String getStudent() throws Exception {
        return controller.getStudentId();
    }

    @Override
    public void startSreenSharing()throws Exception{
        controller.startStreaming();
    }

    @Override
    public void stopScreenSharing() throws Exception {
        controller.stopStreaming();
    }

    @Override
    public void removeStudentObserver(StudentObserver ob) throws Exception{
        examinerService.removeStudentObserver(ob);
    }

    @Override
    public Paper getPaper() throws Exception {
        return examinerService.getPaper();
    }

    @Override
    public String toString() {
        return "StudentObserverImpl{" +
                "studentId=" + controller.getStudentId() +
                '}';
    }
}
