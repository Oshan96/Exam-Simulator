package lk.ijse.examsimulator.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
@Entity
public class Batch_SubDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bs_id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Batch batch;
    @ManyToOne(cascade = CascadeType.ALL)
    private Subject subject;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Exam> exams;

    public Batch_SubDetail() {
    }

    public Batch_SubDetail(Batch batch, Subject subject, List<Exam> exams) {
        this.batch = batch;
        this.subject = subject;
        this.exams = exams;
    }

    public int getBs_id() {
        return bs_id;
    }

    public void setBs_id(int bs_id) {
        this.bs_id = bs_id;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "Batch_SubDetail{" +
                "bs_id=" + bs_id +
                ", batch=" + batch +
                ", subject=" + subject +
                ", exams=" + exams +
                '}';
    }
}
