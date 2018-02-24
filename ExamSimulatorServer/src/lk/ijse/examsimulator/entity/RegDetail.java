package lk.ijse.examsimulator.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
@Entity
public class RegDetail {
    @Id
    private String rid;
    private String reg_date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Batch batch;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ExamDetail> examDetails;

    public RegDetail() {
    }

    public RegDetail(String rid, String reg_date) {
        this.rid = rid;
        this.reg_date = reg_date;
    }

    public RegDetail(String rid, String reg_date, Batch batch, Student student) {
        this.rid = rid;
        this.reg_date = reg_date;
        this.batch = batch;
        this.student = student;
    }

    public RegDetail(String rid, String reg_date, Batch batch, Student student, List<ExamDetail> examDetails) {
        this.rid = rid;
        this.reg_date = reg_date;
        this.batch = batch;
        this.student = student;
        this.examDetails = examDetails;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<ExamDetail> getExamDetails() {
        return examDetails;
    }

    public void setExamDetails(List<ExamDetail> examDetails) {
        this.examDetails = examDetails;
    }

    @Override
    public String toString() {
        return "RegDetail{" +
                "rid='" + rid + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", batch=" + batch +
                ", student=" + student +
                ", examDetails=" + examDetails +
                '}';
    }
}
