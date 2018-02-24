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
public class Lecturer {
    @Id
    private String lecId;
    private String name;
    private String contact;
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Subject> subjects;

    public Lecturer() {
    }

    public Lecturer(String lecId, String name, String contact, String address) {
        this.lecId = lecId;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public Lecturer(String lecId, String name, String contact, String address, List<Subject> subjects) {
        this.lecId = lecId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.subjects = subjects;
    }

    public String getLecId() {
        return lecId;
    }

    public void setLecId(String lecId) {
        this.lecId = lecId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "lecId='" + lecId + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
