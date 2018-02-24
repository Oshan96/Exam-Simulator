package lk.ijse.examsimulator.ui.table_model;

/**
 * Created by oshan on 24-Jan-18.
 *
 * @author oshan
 */
public class PaperDetailModel {
    private String qid;
    private String type;
    private String question;
    private String answer;
    private int marks;

    public PaperDetailModel() {
    }

    public PaperDetailModel(String qid, String type, String question, String answer, int marks) {
        this.qid = qid;
        this.type = type;
        this.question = question;
        this.answer = answer;
        this.marks = marks;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
