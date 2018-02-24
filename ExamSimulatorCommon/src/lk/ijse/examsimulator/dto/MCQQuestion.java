package lk.ijse.examsimulator.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by oshan on 24-Dec-17.
 *
 * @author oshan
 */
public class MCQQuestion implements Serializable{
    private String qid;
    private int marks;
    private String question;
    private String[] options;
    private String answer;
    private String student_answer;

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

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    @Override
    public String toString() {
        return "MCQQuestion{" +
                "qid='" + qid + '\'' +
                ", marks=" + marks +
                ", question='" + question + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answer='" + answer + '\'' +
                ", student_answer='" + student_answer + '\'' +
                '}';
    }
}
