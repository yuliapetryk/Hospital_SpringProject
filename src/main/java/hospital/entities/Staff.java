package hospital.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private  String last_name;
    private  String first_name;
    private  String patronymic;
    private  String email;
    private  String  position;
    private  String  password;

   public Staff( int id, String last_name, String first_name, String patronymic, String  position, String email, String password) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.position =  position;
        this.email = email;
        this.password = password;
    }

    public Staff() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Staff staff = (Staff) obj;
        return id == staff.id &&
                Objects.equals(last_name, staff.last_name) &&
                Objects.equals(first_name, staff.first_name) &&
                Objects.equals(patronymic, staff.patronymic) &&
                Objects.equals(email, staff.email) &&
                Objects.equals(position, staff.position) &&
                Objects.equals(password, staff.password);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
