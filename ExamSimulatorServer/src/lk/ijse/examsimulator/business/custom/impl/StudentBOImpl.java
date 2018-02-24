package lk.ijse.examsimulator.business.custom.impl;

import lk.ijse.examsimulator.business.custom.StudentBO;
import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.entity.User;
import lk.ijse.examsimulator.observer.ExaminerObserver;
import lk.ijse.examsimulator.repository.RepositoryFactory;
import lk.ijse.examsimulator.repository.custom.UserRepository;
import lk.ijse.examsimulator.resources.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class StudentBOImpl implements StudentBO {

    private UserRepository userRepository;

    public StudentBOImpl() {
        userRepository= (UserRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepoType.USER);
    }

    @Override
    public boolean login(String username, String password) throws Exception {
        Session session= HibernateUtil.getSessionFactory().openSession();
        userRepository.setSession(session);
        User user = userRepository.get(username);
        session.close();
        return user != null && user.getPassword().equals(password);
    }
}
