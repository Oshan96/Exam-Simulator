package lk.ijse.examsimulator.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by oshan on 23-Dec-17.
 *
 * @author oshan
 */
@Entity
public class Student {
    @Id
    private String sid;
    private String name;
    private String dob;
    private int age;
    private String gender;
    private String address;
    private String nic;
    private String st_contact;
    private String guardian_name;
    private String gd_contact;

    //@OneToMany -> RegDetail List
    @OneToMany(cascade = CascadeType.ALL)
    private List<RegDetail> regDetails;

    public Student() {
    }

    public Student(String sid, String name, String dob, int age, String gender, String address, String nic, String st_contact, String guardian_name, String gd_contact) {
        this.sid = sid;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.nic = nic;
        this.st_contact = st_contact;
        this.guardian_name = guardian_name;
        this.gd_contact = gd_contact;
    }

    public Student(String sid, String name, String dob, int age, String gender, String address, String nic, String st_contact, String guardian_name, String gd_contact, List<RegDetail> regDetails) {
        this.sid = sid;
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.nic = nic;
        this.st_contact = st_contact;
        this.guardian_name = guardian_name;
        this.gd_contact = gd_contact;
        this.regDetails = regDetails;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getSt_contact() {
        return st_contact;
    }

    public void setSt_contact(String st_contact) {
        this.st_contact = st_contact;
    }

    public String getGuardian_name() {
        return guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

    public String getGd_contact() {
        return gd_contact;
    }

    public void setGd_contact(String gd_contact) {
        this.gd_contact = gd_contact;
    }

    public List<RegDetail> getRegDetails() {
        return regDetails;
    }

    public void setRegDetails(List<RegDetail> regDetails) {
        this.regDetails = regDetails;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", st_contact='" + st_contact + '\'' +
                ", guardian_name='" + guardian_name + '\'' +
                ", gd_contact='" + gd_contact + '\'' +
                ", regDetails=" + regDetails +
                '}';
    }
}
