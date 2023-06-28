package org.drdrapp.userjobservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {
    public static final int START_SEQ = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", initialValue = Company.START_SEQ, allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "companyName")
    String companyName;

    @Column(name = "description")
    String description;

    @Column(name = "is_activity")
    Boolean isActivity;

    @Column(name = "created")
    LocalDateTime created;

    @Column(name = "updated")
    LocalDateTime updated;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("company")
    private List<Userjob> companyUsers;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                ", isActivity=" + isActivity +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!Objects.equals(id, company.id)) return false;
        if (!Objects.equals(companyName, company.companyName)) return false;
        if (!Objects.equals(description, company.description)) return false;
        if (!Objects.equals(isActivity, company.isActivity)) return false;
        if (!Objects.equals(created, company.created)) return false;
        return Objects.equals(updated, company.updated);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isActivity != null ? isActivity.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        return result;
    }
}