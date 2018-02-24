package lk.ijse.examsimulator.entity;

import javax.persistence.*;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
@Entity
public class ExamDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ed_id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Exam exam;
    @ManyToOne(cascade = CascadeType.ALL)
    private RegDetail regDetail;
    private int marks;

    public ExamDetail() {
    }

    public ExamDetail(Exam exam, RegDetail regDetail, int marks) {
        this.exam = exam;
        this.regDetail = regDetail;
        this.marks = marks;
    }

    public int getEd_id() {
        return ed_id;
    }

    public void setEd_id(int ed_id) {
        this.ed_id = ed_id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public RegDetail getRegDetail() {
        return regDetail;
    }

    public void setRegDetail(RegDetail regDetail) {
        this.regDetail = regDetail;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ExamDetail{" +
                "ed_id=" + ed_id +
                ", exam=" + exam +
                ", regDetail=" + regDetail +
                ", marks=" + marks +
                '}';
    }
}
