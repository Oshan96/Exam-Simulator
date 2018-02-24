package lk.ijse.examsimulator.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by oshan on 24-Dec-17.
 *
 * @author oshan
 */
public class Paper implements Serializable{
    private String pid;
    private ArrayList<MCQQuestion> mcqQuestions=new ArrayList<>();
    private ArrayList<CodingQuestion> codingQuestions=new ArrayList<>();
    private int totalMarks;
    private StudentDTO student;

    public Paper() {
    }

    public Paper(Paper paper){
        this.pid=paper.pid;
        this.mcqQuestions=paper.getMcqQuestions();
        this.codingQuestions=paper.getCodingQuestions();
    }

    public Paper(String pid, ArrayList<MCQQuestion> mcqQuestions, ArrayList<CodingQuestion> codingQuestions, int totalMarks) {
        this.pid = pid;
        this.mcqQuestions = mcqQuestions;
        this.codingQuestions = codingQuestions;
        this.totalMarks=totalMarks;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public ArrayList<MCQQuestion> getMcqQuestions() {
        return mcqQuestions;
    }

    public void setMcqQuestions(ArrayList<MCQQuestion> mcqQuestions) {
        this.mcqQuestions = mcqQuestions;
    }

    public ArrayList<CodingQuestion> getCodingQuestions() {
        return codingQuestions;
    }

    public void setCodingQuestions(ArrayList<CodingQuestion> codingQuestions) {
        this.codingQuestions = codingQuestions;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "pid='" + pid + '\'' +
                ", mcqQuestions=" + mcqQuestions +
                ", codingQuestions=" + codingQuestions +
                ", totalMarks=" + totalMarks +
                ", student=" + student +
                '}';
    }
}
