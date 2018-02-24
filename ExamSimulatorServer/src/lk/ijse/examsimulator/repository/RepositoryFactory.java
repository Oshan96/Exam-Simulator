package lk.ijse.examsimulator.repository;

import lk.ijse.examsimulator.repository.custom.ExamDetailRepository;
import lk.ijse.examsimulator.repository.custom.ExamRepository;
import lk.ijse.examsimulator.repository.custom.UserRepository;
import lk.ijse.examsimulator.repository.custom.impl.ExamDetailRepositoryImpl;
import lk.ijse.examsimulator.repository.custom.impl.ExamRepositoryImpl;
import lk.ijse.examsimulator.repository.custom.impl.UserRepositoryImpl;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
public class RepositoryFactory {
    private static RepositoryFactory factory;
    private UserRepository userRepository;
    private ExamRepository examRepository;
    private ExamDetailRepository examDetailRepository;

    public enum RepoType{
        USER,EXAM,EXAM_DETAIL
    }

    private RepositoryFactory(){
        userRepository=new UserRepositoryImpl();
        examRepository=new ExamRepositoryImpl();
        examDetailRepository=new ExamDetailRepositoryImpl();
    }

    public static RepositoryFactory getInstance(){
        if(factory==null)factory=new RepositoryFactory();
        return factory;
    }

    public SuperRepository getRepository(RepoType type){
        switch (type){
            case USER: return userRepository;
            case EXAM: return examRepository;
            case EXAM_DETAIL: return examDetailRepository;
            default:return null;
        }
    }
}
