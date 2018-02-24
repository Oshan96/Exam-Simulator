package lk.ijse.examsimulator.business.custom.impl;

import lk.ijse.examsimulator.business.custom.ExaminerBO;
import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.entity.User;
import lk.ijse.examsimulator.repository.RepositoryFactory;
import lk.ijse.examsimulator.repository.custom.UserRepository;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public class ExaminerBOImpl implements ExaminerBO {
    private static Paper paper;
    private UserRepository userRepository;

    public ExaminerBOImpl() {
        userRepository= (UserRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepoType.USER);
    }

    @Override
    public void uploadPaper(Paper paper) throws Exception {
        ExaminerBOImpl.paper=paper;
    }

    @Override
    public Paper getPaper() throws Exception {
        return ExaminerBOImpl.paper;
    }

    @Override
    public boolean login(String username, String password) throws Exception {
        User user = userRepository.get(username);
        return user != null && user.getPassword().equals(password);
    }
}
