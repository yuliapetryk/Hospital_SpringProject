package hospital.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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



    public Patient(int id,
                   String last_name,
                   String first_name,
                   String patronymic,
                   String sex,
                   String date_of_birth,
                   String phone) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.phone = phone;

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
                Objects.equals(phone, patient.phone) ;
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
                '}';
    }

}
