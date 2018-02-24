package lk.ijse.examsimulator.dto;

import java.io.Serializable;

/**
 * Created by oshan on 24-Dec-17.
 *
 * @author oshan
 */
public class CodingQuestion implements Serializable{
    private String qid;
    private int marks;
    private String question;
    private String answer;
    private String studentSource;

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
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

    public String getStudentSource() {
        return studentSource;
    }

    public void setStudentSource(String studentSource) {
        this.studentSource = studentSource;
    }

    @Override
    public String toString() {
        return "CodingQuestion{" +
                "qid='" + qid + '\'' +
                ", marks=" + marks +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", studentSource='" + studentSource + '\'' +
                '}';
    }
}
