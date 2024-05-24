package hospital.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    private int id;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "sex")
    private String sex;

    @Column(name = "date_of_birth")
    private String date_of_birth;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;



    public Patient(int id,
                   String last_name,
                   String first_name,
                   String patronymic,
                   String sex,
                   String date_of_birth,
                   String phone,
                   String password) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.phone = phone;
        this.password = password;

    }

    public Patient() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient patient = (Patient) obj;
        return id == patient.id &&
                Objects.equals(last_name, patient.last_name) &&
                Objects.equals(first_name, patient.first_name) &&
                Objects.equals(patronymic, patient.patronymic) &&
                Objects.equals(sex, patient.sex) &&
                Objects.equals(date_of_birth, patient.date_of_birth) &&
                Objects.equals(phone, patient.phone) &&
                Objects.equals(password, patient.password);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", sex='" + sex + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDateOfBirth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
