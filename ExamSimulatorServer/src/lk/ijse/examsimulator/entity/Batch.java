package lk.ijse.examsimulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
@Entity
public class Batch {
    @Id
    private String bid;
    private double admission;
    private int bYear;
    private double fee;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RegDetail> regDetails;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Batch_SubDetail> batch_subDetails;


    public Batch() {
    }

    public Batch(String bid, double admission, int bYear, double fee) {
        this.bid = bid;
        this.admission = admission;
        this.bYear = bYear;
        this.fee = fee;
    }

    public Batch(String bid, double admission, int bYear, double fee, List<RegDetail> regDetails) {
        this.bid = bid;
        this.admission = admission;
        this.bYear = bYear;
        this.fee = fee;
        this.regDetails = regDetails;
    }

    public List<Batch_SubDetail> getBatch_subDetails() {
        return batch_subDetails;
    }

    public void setBatch_subDetails(List<Batch_SubDetail> batch_subDetails) {
        this.batch_subDetails = batch_subDetails;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public double getAdmission() {
        return admission;
    }

    public void setAdmission(double admission) {
        this.admission = admission;
    }

    public int getbYear() {
        return bYear;
    }

    public void setbYear(int bYear) {
        this.bYear = bYear;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<RegDetail> getRegDetails() {
        return regDetails;
    }

    public void setRegDetails(List<RegDetail> regDetails) {
        this.regDetails = regDetails;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "bid='" + bid + '\'' +
                ", admission=" + admission +
                ", bYear=" + bYear +
                ", fee=" + fee +
                ", regDetails=" + regDetails +
                ", batch_subDetails=" + batch_subDetails +
                '}';
    }
}
