package lk.ijse.examsimulator.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oshan on 27-Jan-18.
 *
 * @author oshan
 */
@Entity
public class Subject {
    @Id
    private String sub_id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Lecturer lecturer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Batch_SubDetail> batch_subDetails;

    public Subject() {
    }

    public Subject(String sub_id, String name) {
        this.sub_id = sub_id;
        this.name = name;
    }

    public Subject(String sub_id, String name, Lecturer lecturer) {
        this.sub_id = sub_id;
        this.name = name;
        this.lecturer = lecturer;
    }

    public Subject(String sub_id, String name, Lecturer lecturer, List<Batch_SubDetail> batch_subDetails) {
        this.sub_id = sub_id;
        this.name = name;
        this.lecturer = lecturer;
        this.batch_subDetails = batch_subDetails;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public List<Batch_SubDetail> getBatch_subDetails() {
        return batch_subDetails;
    }

    public void setBatch_subDetails(List<Batch_SubDetail> batch_subDetails) {
        this.batch_subDetails = batch_subDetails;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "sub_id='" + sub_id + '\'' +
                ", name='" + name + '\'' +
                ", lecturer=" + lecturer +
                ", batch_subDetails=" + batch_subDetails +
                '}';
    }
}
