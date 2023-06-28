package org.drdrapp.userjobservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userjob_info")
public class Userjob {
    public static final int START_SEQ = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userjob_info_gen")
    @SequenceGenerator(name = "userjob_info_gen", sequenceName = "userjob_info_seq", initialValue = Company.START_SEQ, allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "userjob_info_user_id_fk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("user")
    Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "userjob_info_company_id_fk"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("company")
    Company company;

    @Column(name = "description")
    String description;

    @Column(name = "is_activity")
    Boolean isActivity;

    @Column(name = "created")
    LocalDateTime created;

    @Column(name = "updated")
    LocalDateTime updated;
}