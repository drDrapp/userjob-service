package org.drdrapp.userjobservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
    public static final int START_SEQ = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_gen")
    @SequenceGenerator(name = "users_gen", sequenceName = "users_seq", initialValue = Company.START_SEQ, allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "family_name")
    String familyName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "birthday")
    LocalDate birthday;

    @Column(name = "gender")
    String gender;

    @Column(name = "age")
    Integer age;

    @Column(name = "description")
    String description;

    @Column(name = "is_blocked")
    Boolean isBlocked;

    @Column(name = "created")
    LocalDateTime created;

    @Column(name = "updated")
    LocalDateTime updated;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("user")
    private List<Userjob> userCompanies;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", familyName='" + familyName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", isBlocked=" + isBlocked +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (!Objects.equals(id, users.id)) return false;
        if (!Objects.equals(familyName, users.familyName)) return false;
        if (!Objects.equals(middleName, users.middleName)) return false;
        if (!Objects.equals(firstName, users.firstName)) return false;
        if (!Objects.equals(birthday, users.birthday)) return false;
        if (!Objects.equals(gender, users.gender)) return false;
        if (!Objects.equals(age, users.age)) return false;
        if (!Objects.equals(description, users.description)) return false;
        if (!Objects.equals(isBlocked, users.isBlocked)) return false;
        if (!Objects.equals(created, users.created)) return false;
        return Objects.equals(updated, users.updated);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (familyName != null ? familyName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isBlocked != null ? isBlocked.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        return result;
    }
}