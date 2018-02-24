package lk.ijse.examsimulator.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
@Entity
public class Exam {
    @Id
    private String eid;
    private String eDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ExamDetail> examDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    private Batch_SubDetail batch_subDetail;


    public Exam() {
    }

    public Exam(String eid, String eDate, List<ExamDetail> examDetails) {
        this.eid = eid;
        this.eDate = eDate;
        this.examDetails = examDetails;
    }

    public Exam(String eid, String eDate, List<ExamDetail> examDetails, Batch_SubDetail batch_subDetail) {
        this.eid = eid;
        this.eDate = eDate;
        this.examDetails = examDetails;
        this.batch_subDetail = batch_subDetail;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public List<ExamDetail> getExamDetails() {
        return examDetails;
    }

    public void setExamDetails(List<ExamDetail> examDetails) {
        this.examDetails = examDetails;
    }

    public Batch_SubDetail getBatch_subDetail() {
        return batch_subDetail;
    }

    public void setBatch_subDetail(Batch_SubDetail batch_subDetail) {
        this.batch_subDetail = batch_subDetail;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "eid='" + eid + '\'' +
                ", eDate='" + eDate + '\'' +
                ", examDetails=" + examDetails +
                ", batch_subDetail=" + batch_subDetail +
                '}';
    }
}
