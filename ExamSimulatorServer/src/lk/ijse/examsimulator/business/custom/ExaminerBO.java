package lk.ijse.examsimulator.business.custom;

import lk.ijse.examsimulator.business.SuperBO;
import lk.ijse.examsimulator.dto.Paper;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
public interface ExaminerBO extends SuperBO {
    void uploadPaper(Paper paper) throws Exception;
    Paper getPaper() throws Exception;
}
