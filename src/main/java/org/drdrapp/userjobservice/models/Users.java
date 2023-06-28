package org.drdrapp.userjobservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
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
    @JsonManagedReference
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
}