package lk.ijse.examsimulator.business;

import lk.ijse.examsimulator.business.custom.impl.ExaminerBOImpl;
import lk.ijse.examsimulator.business.custom.impl.StudentBOImpl;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class BOFactory {
    private static BOFactory factory;

    public enum BOType{
        EXAMINER,STUDENT
    }

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if(factory==null)factory=new BOFactory();
        return factory;
    }

    public SuperBO getBO(BOType type){
        switch (type){
            case STUDENT: return new StudentBOImpl();
            case EXAMINER: return new ExaminerBOImpl();
            default:return null;
        }
    }
}
