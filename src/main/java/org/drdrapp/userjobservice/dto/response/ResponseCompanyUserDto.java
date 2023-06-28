package org.drdrapp.userjobservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseCompanyUserDto {
    private Long id;
    private String familyName;
    private String middleName;
    private String firstName;
    private LocalDate birthday;
    private String gender;
    private Integer age;
    private String description;
    private Boolean isBlocked;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String linkDescription;
    private Boolean linkIsActivity;

    public ResponseCompanyUserDto(Long id,
                                  String familyName,
                                  String middleName,
                                  String firstName,
                                  LocalDate birthday,
                                  String gender,
                                  Integer age,
                                  String description,
                                  Boolean isBlocked,
                                  LocalDateTime created,
                                  LocalDateTime updated,
                                  String linkDescription,
                                  Boolean linkIsActivity) {
        this.id = id;
        this.familyName = familyName;
        this.middleName = middleName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.isBlocked = isBlocked;
        this.created = created;
        this.updated = updated;
        this.linkDescription = linkDescription;
        this.linkIsActivity = linkIsActivity;
    }
}