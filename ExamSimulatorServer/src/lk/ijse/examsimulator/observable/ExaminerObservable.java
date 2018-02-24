package lk.ijse.examsimulator.observable;

import lk.ijse.examsimulator.observer.ExaminerObserver;
import java.util.ArrayList;

/**
 * Created by oshan on 28-Dec-17.
 *
 * @author oshan
 */
public class ExaminerObservable implements SuperObservable<ExaminerObserver>{
    private ArrayList<ExaminerObserver> observables;

    public ExaminerObservable() {
        observables = new ArrayList<>();
    }


    @Override
    public void addObserver(ExaminerObserver observer) throws Exception {
        observables.add(observer);
    }

    @Override
    public void removeObserver(ExaminerObserver observer) throws Exception {
        observables.remove(observer);
    }

    public void notifyRestrictedAppBehaviour(String studentId)throws Exception{
        for (ExaminerObserver ob:observables){
            ob.notifyRestrictedAppBehaviour(studentId);
        }
    }

    public void streamScreen(byte[] imageStream) throws Exception{
        for(ExaminerObserver ob:observables){
            ob.streamScreen(imageStream);
        }
    }

    public void informStudentLogin(String studentId) throws Exception{
        for(ExaminerObserver ob:observables){
            ob.informStudentLogin(studentId);
        }
    }

    public void notifyStudentListUpdate() throws Exception{
        for(ExaminerObserver ob:observables){
            ob.notifyUIUpdate();
        }
    }
}
