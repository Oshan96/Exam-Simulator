package lk.ijse.examsimulator.observable;

import lk.ijse.examsimulator.observer.StudentObserver;

import java.util.ArrayList;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class StudentObservable implements SuperObservable<StudentObserver>{
    private ArrayList<StudentObserver> observers=new ArrayList<>();

    private static StudentObserver currentStreamer;

    @Override
    public synchronized void addObserver(StudentObserver observer) throws Exception {
        observers.add(observer);
    }

    @Override
    public synchronized void removeObserver(StudentObserver observer) throws Exception {
        if(observer.equals(currentStreamer))
            currentStreamer=null;
        observers.remove(observer);

    }

    public void requestScreenShare(String student) throws Exception{

        for (StudentObserver ob:observers){
            if(ob.getStudent().equals(student)){
                ob.startSreenSharing();
                if(currentStreamer!=null && !currentStreamer.getStudent().equals(student)) {
                    currentStreamer.stopScreenSharing();
                    currentStreamer = ob;
                }else if(currentStreamer==null){
                    currentStreamer=ob;
                }
            }
        }

    }

    public synchronized ArrayList<StudentObserver> getObservers() throws Exception{
        return observers;
    }
}
